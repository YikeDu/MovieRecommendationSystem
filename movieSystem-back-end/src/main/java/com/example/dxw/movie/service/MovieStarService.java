package com.example.dxw.movie.service;

import com.example.dxw.movie.pojo.MovieStar;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


public interface MovieStarService extends IService<MovieStar> {

    void inert(Map map);

    void collect(Map map);
}
