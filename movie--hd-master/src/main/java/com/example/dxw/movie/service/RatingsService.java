package com.example.dxw.movie.service;

import com.example.dxw.movie.pojo.Ratings;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface RatingsService extends IService<Ratings> {
    Object getdata(String type, String cid);

    Object like(Map map);

    Object getclassification(String type);

    Object getlb(String mid);
}
