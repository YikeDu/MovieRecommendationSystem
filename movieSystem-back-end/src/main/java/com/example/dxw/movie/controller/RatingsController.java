package com.example.dxw.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.example.dxw.https.dxw;
import com.example.dxw.movie.mapper.*;
import com.example.dxw.movie.pojo.*;
import com.example.dxw.movie.service.CommentService;
import com.example.dxw.movie.service.MovieStarService;
import com.example.dxw.movie.service.RatingsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/ratings")
public class RatingsController {
    @Autowired
    private RatingsService ratingsService;
    @Autowired
    private MovieStarService movieStarService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PMovieMapper pMovieMapper;
    @Autowired
    private RatingsMapper ratingsMapper;
    @Autowired
    private MovieStarMapper movieStarMapper;
    @Autowired
    private MoviesMapper moviesMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PMovieXstjMapper pMovieXstjMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CollectMapper collectMapper;

    @ApiOperation(value = "postmb模板")
    @GetMapping("lb")/**///postmb模板
    public Object lb(String mid, String uid) throws Exception {
        return ratingsService.getlb(mid, uid);
    }
    @ApiOperation(value = "classification页面加载数据")
    @GetMapping("classification")/**///分类数据
    public Object classification(String type) throws Exception {

        return ratingsService.getclassification(type);
    }

    @ApiOperation(value = "classification页面加载数据")
    @GetMapping("classification2")/**///分类数据
    public Object classification2(int currentPage,int pageSize,String type) throws Exception {

        return ratingsService.classification2(currentPage,pageSize,type);
    }


    @ApiOperation(value = "jsData接受点赞的数据")
    @PostMapping("jsData")/**///postmb模板
    public Object jsData(@RequestBody Map map) throws Exception {
        movieStarService.inert(map);
        return null;
    }
    @ApiOperation(value = "收藏的数据")
    @PostMapping("collect")/**///postmb模板
    public Object collect(@RequestBody Map map) throws Exception {
        movieStarService.collect(map);
        return null;
    }

    @ApiOperation(value = "你的点赞数据")
    @PostMapping("like")/**///getdata返回前端数据
    public Object like(@RequestBody Map map) throws Exception {
        return ratingsService.like(map);
    }

    @ApiOperation(value = "删除喜欢数据", notes = "")
    @GetMapping("deletM")//
    public Object deletM(String id) throws Exception {
        return ratingsService.deletM(id);
    }

    @ApiOperation(value = "评论数据")
    @PostMapping("sumbit")/**///getdata返回前端数据
    public Object sumbit(@RequestBody Map map) throws Exception {
        System.out.println("map = " + map);
        return commentService.insert(map);
    }

    @ApiOperation(value = "页面加载喜好数据数据!!")
    @GetMapping("getxh")/**///getmb模板
    public Object getxh(String cid) throws Exception {
        String pj = "https://image.tmdb.org/t/p/w500";
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getId, cid));
        LinkedList<Object> xz = new LinkedList<>();
        LinkedHashMap<Object, Object> map3 = new LinkedHashMap<>();
        for (User user : users) {
            String xh = user.getXh();
            if (xh != null) {
                String[] split = user.getXh().split(",");

                for (String s : split) {
                    xz.add(s);
                    map3.put(s, s);
                }
            }
        }
        System.out.println(map3);
        List<Movies> movies = moviesMapper.selectList(new LambdaQueryWrapper<Movies>().select(Movies::getGenres).groupBy(Movies::getGenres));
        System.out.println(movies.size());
        LinkedHashMap map = new LinkedHashMap();
        for (Movies movie : movies) {
            String genres = movie.getGenres();
            String[] split = genres.split("\\|");
            for (String s : split) {
                map.put(s, s);
            }
        }
        LinkedList<Object> objects = new LinkedList<>();
        LinkedHashMap<Object, Object> map2 = new LinkedHashMap<>();
        for (Object key : map.keySet()) {
            System.out.println("Key = " + key);
            List<Movies> movies1 = moviesMapper.selectList(new LambdaQueryWrapper<Movies>().like(Movies::getGenres, key));
            if (movies1.size() > 0) {
                Movies movies2 = movies1.get(0);
                String movieid = movies2.getMovieid();
                int size = map2.size();
                map2.put(movieid, movieid);
                int size2 = map2.size();
                if (size2 > size) {
                    LinkedHashMap<Object, Object> map1 = new LinkedHashMap<>();
                    List<PMovieDO> pMovieDOS = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid, movieid));
                    PMovieDO pMovieDO = pMovieDOS.get(0);

                    map1.put("url", pj + pMovieDO.getImageSrc2());
                    map1.put("name", pMovieDO.getName());
                    map1.put("title", key);
                    Object o = map3.get(key);
                    if (o != null) {
                        map1.put("color", "red");
                    } else {
                        map1.put("color", "#66B1FF");
                    }
                    objects.add(map1);
                } else {
                    movies2 = movies1.get(2);
                    movieid = movies2.getMovieid();
                    size = map2.size();
                    map2.put(movieid, movieid);
                    size2 = map2.size();
                    if (size2 > size) {
                        LinkedHashMap<Object, Object> map1 = new LinkedHashMap<>();
                        List<PMovieDO> pMovieDOS = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid, movieid));
                        PMovieDO pMovieDO = pMovieDOS.get(0);

                        map1.put("url", pj + pMovieDO.getImageSrc2());
                        map1.put("name", pMovieDO.getName());
                        map1.put("title", key);
                        Object o = map3.get(key);
                        if (o != null) {
                            map1.put("color", "red");

                        } else {
                            map1.put("color", "#66B1FF");
                        }

                        objects.add(map1);
                    } else {
                        movies2 = movies1.get(3);
                        movieid = movies2.getMovieid();
                        size = map2.size();
                        map2.put(movieid, movieid);
                        size2 = map2.size();
                        if (size2 > size) {
                            LinkedHashMap<Object, Object> map1 = new LinkedHashMap<>();
                            List<PMovieDO> pMovieDOS = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid, movieid));
                            PMovieDO pMovieDO = pMovieDOS.get(0);
                            map1.put("url", pj + pMovieDO.getImageSrc2());
                            map1.put("name", pMovieDO.getName());
                            map1.put("title", key);
                            Object o = map3.get(key);
                            if (o != null) {
                                map1.put("color", "red");

                            } else {
                                map1.put("color", "#66B1FF");
                            }
                            objects.add(map1);
                        }
                    }
                }
            }
        }
        LinkedHashMap<Object, Object> map1 = new LinkedHashMap<>();
        map1.put("types", objects);
        map1.put("xz", xz);
        return ResBean.success("Succeed", map1);
    }
    @ApiOperation(value = "收藏数据!!")
    @GetMapping("getCollect")/**///getmb模板
    public Object getCollect(int currentPage,int pageSize,HttpServletRequest request) throws Exception {
        return ratingsService.getCollect( currentPage, pageSize,request);
    }

    @ApiOperation(value = "新开页面的打分数据和评论数据!!")
    @GetMapping("getxk")/**///getmb模板
    public Object getxk(String cid, String mid) throws Exception {

        return ratingsService.getxk(cid, mid);
    }

    //    =======================================================>
    @ApiOperation(value = "hom页面加载数据")
    @GetMapping("HomePage")/**///HomePage
    public Object getHomePage(HttpServletRequest request) throws Exception {
        long now = dxw.now();
        Object homePage = ratingsService.getHomePage(request);
        long now2 = dxw.now2(now);
        return homePage;
    }
    @ApiOperation(value = "页面加载数据")
    @GetMapping("mid")/**///HomePage
    public Object mid(String mid,HttpServletRequest request) throws Exception {
        return ratingsService.mid(mid,request);
    }
    @ApiOperation(value = "页面加载数据")
    @GetMapping("getLb")/**///HomePage
    public Object getLb(String mid,HttpServletRequest request) throws Exception {
        return ratingsService.getLb(mid,request);
    }

    @ApiOperation(value = "搜索", notes = "")
    @GetMapping("searching")//搜索searching
    public Object searching(String search) throws Exception {
        return ratingsService.searching(search);
    }
    @ApiOperation(value = "喜好数据")
    @GetMapping("getXh")/**///HomePage
    public Object getXh(int currentPage,int pageSize,HttpServletRequest request) throws Exception {
        return ratingsService.getXh( currentPage, pageSize,request);
    }
    @ApiOperation(value = "喜好数据")
    @GetMapping("simUserIds")/**///HomePage
    public Object simUserIds (int currentPage,int pageSize,HttpServletRequest request) throws Exception {
        long now = dxw.now();
        Object o = ratingsService.simUserIds(currentPage, pageSize, request);
        long l = dxw.now2(now);
        return o;
    }
    @ApiOperation(value = "喜好数据")
    @GetMapping("userID")/**///HomePage
    public Object userID (int currentPage,int pageSize,int userid,HttpServletRequest request) throws Exception {
        return ratingsService.userID ( currentPage, pageSize,userid,request);
    }
    @ApiOperation(value = "上传头像", notes = "")
    @PostMapping("upload")//
    public Object upload(MultipartFile file, HttpServletRequest request) throws Exception {
        return ratingsService.upload(file, request);
    }
}

