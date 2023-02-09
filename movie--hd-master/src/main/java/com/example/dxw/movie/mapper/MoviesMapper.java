package com.example.dxw.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dxw.movie.pojo.Movies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MoviesMapper extends BaseMapper<Movies> {
    @Select("SELECT * FROM `movies` WHERE genres LIKE #{genres}")
    List<Movies> movis(String genres);

}
