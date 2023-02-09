package com.example.dxw.movie.service;

import com.example.dxw.movie.pojo.MovieStar;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface MovieStarService extends IService<MovieStar> {

    void inert(Map map);
}
