package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.VideoRepository;
import com.wlgzs.huipeasant.entity.Video;
import com.wlgzs.huipeasant.service.VideoService;
import com.wlgzs.huipeasant.util.CheckImage;
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

    @Override
    public Page getVideoListPage(String videoKeyWord, int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "productId");
        Pageable pageable = new PageRequest(page, limit, sort);
        Specification<Video> specification = new PageUtil<Video>(videoKeyWord).getPage("modelTitle","videoIntroduction");
        Page<Video> pages = videoRepository.findAll(specification, pageable);
        return pages;
    }

    @Override
    public void saveVideo(MultipartFile[] myFileNames, HttpSession session, HttpServletRequest request) {
        String realName = "";
        String[] str = new String[myFileNames.length];
        CheckImage checkImage = new CheckImage();
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
                if(checkImage.verifyImage(fileName)){
                    video.setVideoCover(realName);
                }else if(checkImage.isVedioFile(fileName)){
                    video.setVideoAddress(realName);
                }
                // "/upload"是你自己定义的上传目录
                String realPath = session.getServletContext().getRealPath("/upload");
                File uploadFile = new File(realPath, realName);
                try {
                    myFileNames[i].transferTo(uploadFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    @Override
    public void delete(long videoId, HttpServletRequest request) {
        Video video = videoRepository.findById(videoId);
        if(video != null){
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
    public void edit(long videoId, MultipartFile[] myFileNames, HttpSession session, HttpServletRequest request) {



    }

//    @Override
//    public List<Video> videoList() {
//        List<Video> videos = videoRepository.findAllVideo();
//        videos = videos.subList(videos.size()-8,videos.size());
//        return videos;
//    }
}
