package com.example.dxw;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dxw.https.Methods;
import com.example.dxw.https.dxw;
import com.example.dxw.movie.mapper.*;
import com.example.dxw.movie.pojo.PMovieDO;
import com.example.dxw.movie.pojo.PMovieDO2;
import com.example.dxw.movie.pojo.Ratings;
import com.example.dxw.movie.service.impl.MailImpl;
import com.example.dxw.movie.service.impl.RatingsServiceImpl;
import org.junit.jupiter.api.Test;
import org.python.core.PyFunction;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class DTests2 {
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
//
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("D:\\1.py");
//        PyFunction func = interpreter.get("open",PyFunction.class);
//        String searchStr = Methods.generalGet("https://api.themoviedb.org/3/trending/movie/week?api_key=047cc1d00267ec4a18b7791675dc1566");
//        String tjJson = Methods.generalGet("https://api.themoviedb.org/3/movie/" + 3 + "/recommendations?api_key=047cc1d00267ec4a18b7791675dc1566&language=en-US&page=1");
//        String xsJson = Methods.generalGet("https://api.themoviedb.org/3/movie/" + 3 + "/similar?api_key=047cc1d00267ec4a18b7791675dc1566&language=en-US&page=1");
//        String setHearf = Methods.generalGet("https://api.themoviedb.org/3/movie/" + 3 + "/videos?api_key=047cc1d00267ec4a18b7791675dc1566&language=en-US");
//
//        System.out.println("searchStr = " + tjJson);

//        List<PMovieDO> pMovieDOS = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getFz, "5").last("limit 7"));

//        List<PMovieDO> pMovieDOS1 = pMovieMapper.selectList(null);
        long now = dxw.now();
        List<Ratings> ratings = ratingsMapper.selectList(new LambdaQueryWrapper<Ratings>().select(Ratings::getMovieid).groupBy(Ratings::getMovieid));
        List<PMovieDO2> linkedList = new ArrayList<>();
        for (Ratings rating : ratings) {
            String movieid = rating.getMovieid();
            List<Ratings> ratings2 = ratingsMapper.selectList(new LambdaQueryWrapper<Ratings>().eq(Ratings::getMovieid, movieid));
            int fz = 0;
            for (Ratings ratings1 : ratings2) {
                fz += ratings1.getRating();
            }
            PMovieDO2 pMovieDO2 = new PMovieDO2();
            pMovieDO2.setFz(fz / ratings2.size());
            pMovieDO2.setMovid(movieid);
            linkedList.add(pMovieDO2);
        }
        LinkedList<Object> linkedList2 = new LinkedList<>();
        for (PMovieDO2 pMovieDO2 : linkedList) {
            Integer fz = pMovieDO2.getFz();
            if (fz==5){
                linkedList2.add(pMovieDO2);
            }
        }
        for (PMovieDO2 pMovieDO2 : linkedList) {
            Integer fz = pMovieDO2.getFz();
            if (fz==4){
                linkedList2.add(pMovieDO2);
            }
        }
        for (PMovieDO2 pMovieDO2 : linkedList) {
            Integer fz = pMovieDO2.getFz();
            if (fz==3){
                linkedList2.add(pMovieDO2);
            }
        }
        for (PMovieDO2 pMovieDO2 : linkedList) {
            Integer fz = pMovieDO2.getFz();
            if (fz==2){
                linkedList2.add(pMovieDO2);
            }
        }
        for (PMovieDO2 pMovieDO2 : linkedList) {
            Integer fz = pMovieDO2.getFz();
            if (fz==1){
                linkedList2.add(pMovieDO2);
            }
        }

        dxw.now2(now);
//        for (PMovieDO2 pMovieDO2 : linkedList) {
//            System.out.println(pMovieDO2);
//        }
    }
}
