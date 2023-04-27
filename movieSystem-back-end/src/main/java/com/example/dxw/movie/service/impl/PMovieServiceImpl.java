package com.example.dxw.movie.service.impl;

import com.example.dxw.movie.pojo.PMovieDO;
import com.example.dxw.movie.mapper.PMovieMapper;
import com.example.dxw.movie.service.IPMovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class PMovieServiceImpl extends ServiceImpl<PMovieMapper, PMovieDO> implements IPMovieService {

}
