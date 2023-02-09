package com.example.dxw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.dxw.https.General;
import com.example.dxw.https.Methods;
import com.example.dxw.movie.mapper.SimUsersMapper;
import com.example.dxw.movie.mapper.UserMapper;
import com.example.dxw.movie.pojo.SimUser;
import com.example.dxw.movie.pojo.User;
import com.example.dxw.movie.service.impl.RatingsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class DTests {
    @Autowired
    private SimUsersMapper simUsersMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RatingsServiceImpl ratingsService;

    @Test
    void contextLoads() {
//        User user = new User();
//        List<RatingsServiceImpl.RcdItem> rcdItems = ratingsService.rcdFilm(user);

//        String url = "https://api.themoviedb.org/3/movie/555?api_key=047cc1d00267ec4a18b7791675dc1566";
//        String mid = General.mid;
//        Object image1 = JSONObject.parseObject(mid).get("backdrop_path");
//        Object image2 = JSONObject.parseObject(mid).get("poster_path");
//        String imageSrc1 = "https://image.tmdb.org/t/p/w500" + image1;
//        String imageSrc2 = "https://image.tmdb.org/t/p/w500" + image2;
//
//        Object jj = JSONObject.parseObject(mid).get("overview");
//        Object language = JSONObject.parseObject(mid).get("original_language");
//        Object title = JSONObject.parseObject(mid).get("original_title");
////        电影名
//        Object name = JSONObject.parseObject(mid).getJSONObject("belongs_to_collection").get("name");
//        JSONArray jsonArray = JSONObject.parseObject(mid).getJSONArray("genres");
//        List<String> mtypeLis = new LinkedList<>();
//        for (Object o : jsonArray) {
//            Object mtype = JSONObject.parseObject(mid).get("name");
//            mtypeLis.add(mtype.toString());
//        }
//        System.out.println(jj);
//        List<SimUser> simUsers = simUsersMapper.selectBatchIds(null);
//        List<User> users = userMapper.selectList(null);
//        System.out.println(users);
    }
}
