package com.example.dxw.movie.service;

import com.example.dxw.movie.pojo.Ratings;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface RatingsService extends IService<Ratings> {


    Object like(Map map);

    Object getclassification(String type);

    Object getlb(String mid,String uid);

    Object deletM(String id);

    Object getxk(String cid, String mid);

    Object getHomePage(HttpServletRequest request);

    Object classification2(int currentPage,int pageSize,String type);

    Object mid(String mid, HttpServletRequest request);

    Object getLb(String mid,HttpServletRequest request);

    Object searching(String search);

    Object getXh(int currentPage,int pageSize,HttpServletRequest request);

    Object simUserIds(int currentPage, int pageSize, HttpServletRequest request);

    Object userID(int currentPage, int pageSize, int userid, HttpServletRequest request);

    Object upload(MultipartFile file, HttpServletRequest request);

    Object getCollect(int currentPage, int pageSize, HttpServletRequest request);
}
