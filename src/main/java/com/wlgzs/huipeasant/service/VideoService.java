package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-07-13 8:13
 * @description:
 **/
public interface VideoService {
    //遍历视频(前台)
//    List<Video> videoList();

    //遍历视频(后台)
    Page getVideoListPage(String videoKeyWord, int page, int limit);

    //添加视频
    void saveVideo(MultipartFile[] myFileNames, HttpSession session, HttpServletRequest request);

    //删除视频
    void delete(long videoId,HttpServletRequest request);

    //修改视频
    void edit(long videoId, MultipartFile[] myFileNames, HttpSession session,HttpServletRequest request);

}
