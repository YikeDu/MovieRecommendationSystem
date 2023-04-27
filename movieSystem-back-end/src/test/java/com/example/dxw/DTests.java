package com.example.dxw;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dxw.https.Methods;
import com.example.dxw.https.dxw;
import com.example.dxw.movie.mapper.*;
import com.example.dxw.movie.pojo.*;
import com.example.dxw.movie.service.impl.MailImpl;
import com.example.dxw.movie.service.impl.RatingsServiceImpl;
import org.junit.jupiter.api.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.attribute.standard.NumberUp;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@SpringBootTest
class DTests {
    @Autowired
    private SimUsersMapper simUsersMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RatingsServiceImpl ratingsService;
    @Autowired
    private PMovieMapper pMovieMapper;
    @Autowired
    private RatingsMapper ratingsMapper;
    @Autowired
    private PMovieXstjMapper pMovieXstjMapper;
    @Autowired
    private MoviesMapper moviesMapper;
    @Autowired
    private LbMapper lbMapper;
    @Autowired
    private MailImpl mail;

    @Test
    void contextLoads() throws UnsupportedEncodingException {
//        String er = Str.er;
//        JSONArray jsonArray = JSONObject.parseObject(er).getJSONArray("results");
//        int i=0;
//        for (Object o : jsonArray) {
//            Object id = JSONObject.parseObject(o.toString()).get("id");
////
//            PMovieDO pMovieDO = pMovieMapper.selectOne(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid,id).last("limit 1"));
//
//            if (pMovieDO!=null){
//                pMovieDO.setRouting("rm");
//                pMovieMapper.updateById(pMovieDO);
////                System.out.println("pMovieDO = " + pMovieDO);
//                System.out.println("i = " + i);
//                i++;
//            }
//        }
        long now = dxw.now();
//        List<PMovieDO> pMovieDOS = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getFz, "5").last("limit 7"));
//
//        List<PMovieDO> pMovieDOSrm = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getRouting, "rm").last("limit 7"));
//        List<PMovieDO> pMovieDOStj = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getFz, "5").last("limit 7,7"));
        List<PMovieDO> pMovieDOS = pMovieMapper.selectList(null);
        for (PMovieDO pMovieDO : pMovieDOS) {
            List<Movies> movies = moviesMapper.selectList(new LambdaQueryWrapper<Movies>().like(Movies::getMovieid,pMovieDO.getMovieid()));
            if (movies.size()>0){
                Movies movies1 = movies.get(0);
                pMovieDO.setGenres(movies1.getGenres());
                pMovieMapper.updateById(pMovieDO);
            }
        }
        long l = dxw.now2(now);
    }
}
