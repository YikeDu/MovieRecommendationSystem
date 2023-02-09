package com.example.dxw.movie.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dxw.movie.mapper.MoviesMapper;
import com.example.dxw.movie.mapper.RatingsMapper;
import com.example.dxw.movie.pojo.MovieStar;
import com.example.dxw.movie.mapper.MovieStarMapper;
import com.example.dxw.movie.pojo.Ratings;
import com.example.dxw.movie.service.MovieStarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MovieStarServiceImpl extends ServiceImpl<MovieStarMapper, MovieStar> implements MovieStarService {
    @Autowired
    private MovieStarMapper movieStarMapper;
    @Autowired
    private RatingsMapper ratingsMapper;

    @Override
    public void inert(Map map) {
        System.out.println(map);
        Integer id = Integer.valueOf(map.get("id").toString());
        String star = map.get("star").toString();
        String cid = map.get("cid").toString();
        System.out.println("cid:"+cid);
//        String mid = map.get("mid").toString();
        String year = map.get("year").toString();
        String title = map.get("title").toString();
        String name = map.get("name").toString();
//        String mtypeLis = map.get("mtypeLis").toString();
        String movieid = map.get("movieid").toString();
//        String runtime = map.get("runtime").toString();
        String imageSrc1 = map.get("imageSrc1").toString();
        String imageSrc2 = map.get("imageSrc2").toString();
        String jj = map.get("jj").toString();
        String language = map.get("language").toString();
        String voteCount = map.get("voteCount").toString();
//        String datas = JSON.toJSONString(map);
        String datas = map.toString();
        List<MovieStar> movieStars = movieStarMapper.selectList(new LambdaQueryWrapper<MovieStar>().eq(MovieStar::getMid,id).eq(MovieStar::getCid,cid));
        System.out.println(movieStars);
        if (movieStars.size()>0){
            MovieStar movieStar = movieStars.get(0);
            movieStar.setStar(star);
            movieStar.setDatas(datas);
            movieStarMapper.updateById(movieStar);
        }else {
            MovieStar movieStar = new MovieStar();
            movieStar.setStar(star);
            movieStar.setDatas(datas);
            movieStar.setMid(id.toString());
            movieStar.setMovie(id.toString());
            movieStar.setYear(year);
//            movieStar.setRuntime(runtime);
            movieStar.setImagesrc1(imageSrc1);
            movieStar.setImagesrc2(imageSrc2);
            movieStar.setJj(jj);
            movieStar.setLanguage(language);
            movieStar.setTitle(title);
            movieStar.setName(name);
            movieStar.setVotecount(voteCount);
            movieStar.setCid(cid);
//            movieStar.setMtypelis(mtypeLis);
            movieStarMapper.insert(movieStar);
        }
        List<Ratings> ratings1 = ratingsMapper.selectList(new LambdaQueryWrapper<Ratings>().orderByDesc(Ratings::getId).last("limit 1"));
        Ratings ratings = new Ratings();
        Ratings ratings2 = ratings1.get(0);
        ratings.setIndexl(ratings2.getId()+"");
        ratings.setUserid(cid);
        ratings.setMovieid(movieid);
        ratings.setRating(Integer.valueOf(star));
        ratings.setTimestamp(id.toString());
        ratingsMapper.insert(ratings);
    }
}
