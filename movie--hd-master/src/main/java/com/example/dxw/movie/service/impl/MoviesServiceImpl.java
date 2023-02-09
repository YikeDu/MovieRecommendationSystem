package com.example.dxw.movie.service.impl;

import com.example.dxw.movie.pojo.Movies;
import com.example.dxw.movie.mapper.MoviesMapper;
import com.example.dxw.movie.service.MoviesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MoviesServiceImpl extends ServiceImpl<MoviesMapper, Movies> implements MoviesService {

}
