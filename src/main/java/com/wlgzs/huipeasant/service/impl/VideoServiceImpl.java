package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.VideoRepository;
import com.wlgzs.huipeasant.entity.Video;
import com.wlgzs.huipeasant.service.VideoService;
import com.wlgzs.huipeasant.util.CheckImage;
import com.wlgzs.huipeasant.util.IoUtil;
import com.wlgzs.huipeasant.util.PageUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author:胡亚星
 * @createTime 2018-07-13 8:16
 * @description:
 **/
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    //遍历视频(后台)
    @Override
    public Page getVideoListPage(String videoKeyWord, int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "videoId");
        Pageable pageable = new PageRequest(page, limit, sort);
        Specification<Video> specification = new PageUtil<Video>(videoKeyWord).getPage("modelTitle", "videoIntroduction");
        Page<Video> pages = videoRepository.findAll(specification, pageable);
        return pages;
    }

    //上传视频
    @Override
    public void saveVideo(MultipartFile[] myFileNames, HttpSession session, HttpServletRequest request) {
        String realName = "";
        String[] str = new String[myFileNames.length];
        CheckImage checkImage = new CheckImage();
        IoUtil ioUtil = new IoUtil();
        Map<String, String[]> properties = request.getParameterMap();
        Video video = new Video();
        try {
            BeanUtils.populate(video, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        for (int i = 0;i < myFileNames.length;i++){
            if (!myFileNames[i].getOriginalFilename().equals("")) {
                String fileName = myFileNames[i].getOriginalFilename();
                String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());
                // 生成实际存储的真实文件名
                realName = UUID.randomUUID().toString() + fileNameExtension;
                // "/upload"是你自己定义的上传目录
                String realPath = session.getServletContext().getRealPath("/upload");
                System.out.println("12312====="+realPath);
                String videoUrl = "/upload/video/" + video.getVideoTitle() + "/" + realName;
                ioUtil.saveFile(myFileNames[i],videoUrl);
//                File uploadFile = new File(realPath, realName);
//                try {
//                    myFileNames[i].transferTo(uploadFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if(checkImage.verifyImage(fileName)){
                    video.setVideoCover(request.getContextPath() + "" + videoUrl);
                }else if(checkImage.isVedioFile(fileName)){
                    video.setVideoAddress(request.getContextPath() + "" + videoUrl);
                }
            }else{
                System.out.println("没有文件");
            }
        }
        //上传时间
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = dateFormat.format(new Date());
        try {
            Date date = dateFormat.parse(time);
            video.setUploadTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        videoRepository.save(video);
    }

    //删除视频
    @Override
    public void delete(long videoId, HttpServletRequest request) {
        Video video = videoRepository.findById(videoId);
        if (video != null) {
            String path = request.getSession().getServletContext().getRealPath("/");
            String image = video.getVideoCover();
            String video1 = video.getVideoAddress();
            File file1 = new File(path + "" + image);
            File file2 = new File(path + "" + video1);
            if (file1.exists() && file1.isFile()) {
                file1.delete();
            }
            if (file2.exists() && file2.isFile()) {
                file2.delete();
            }
            videoRepository.deleteById(videoId);
        }
    }

    //修改视频
    @Override
    public void edit(long videoId, MultipartFile myFileName, HttpSession session, HttpServletRequest request) {
        Video video = videoRepository.findById(videoId);
        Map<String, String[]> properties = request.getParameterMap();
        try {
            BeanUtils.populate(video, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        CheckImage checkImage = new CheckImage();
        String realName = "";
        if (video != null) {
            if (!myFileName.getOriginalFilename().equals("")) {
                String fileName = myFileName.getOriginalFilename();
                String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());
                // 生成实际存储的真实文件名
                realName = UUID.randomUUID().toString() + fileNameExtension;
                // "/upload"是你自己定义的上传目录
                String realPath = session.getServletContext().getRealPath("/upload");
                File uploadFile = new File(realPath, realName);
                try {
                    myFileName.transferTo(uploadFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                video.setVideoCover(realPath+"/"+realName);
            }
            videoRepository.save(video);
        }
    }

    //遍历视频(前台)
    @Override
    public List<Video> videoList() {
        return videoRepository.findAll();
    }

    //视频详情页
    @Override
    public Video detailsVideo(long videoId) {
        //增加视频点击量
        videoRepository.addVideoHits(videoId);
        return videoRepository.findById(videoId);
    }

    //视频排行
    @Override
    public List<Video> videoRanking() {
        //按点击量排序
        Sort sort = new Sort(Sort.Direction.DESC,"videoHits");
        List<Video> videos = videoRepository.findAll(sort);
        return videos;
    }
}
