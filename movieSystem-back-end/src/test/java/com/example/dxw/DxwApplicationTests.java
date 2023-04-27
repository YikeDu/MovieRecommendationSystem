package com.example.dxw;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dxw.https.Methods;
import com.example.dxw.movie.mapper.MoviesMapper;
import com.example.dxw.movie.mapper.PMovieXstjMapper;
import com.example.dxw.movie.mapper.SimUsersMapper;
import com.example.dxw.movie.pojo.Movies;
import com.example.dxw.movie.pojo.PMovieXstjDO;
import com.example.dxw.movie.pojo.SimUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class DxwApplicationTests {
    @Autowired
    private MoviesMapper moviesMapper;
    @Autowired
    private SimUsersMapper simUsersMapper;
    @Autowired
    private PMovieXstjMapper pMovieXstjMapper;
    @Test
    void contextLoads() {
        List<PMovieXstjDO> pMovieXstjDOS = pMovieXstjMapper.selectList(null);
        for (PMovieXstjDO pMovieXstjDO : pMovieXstjDOS) {
            String movieid = pMovieXstjDO.getMovieid();
            List<PMovieXstjDO> pMovieXstjDOS1 = pMovieXstjMapper.selectList(new LambdaQueryWrapper<PMovieXstjDO>()
                    .eq(PMovieXstjDO::getMovieid,movieid));
            LinkedList<Integer> linkedList = new LinkedList<>();
            for (PMovieXstjDO movieXstjDO : pMovieXstjDOS1) {
                Integer id = movieXstjDO.getId();
                linkedList.add(id);
            }
            int i=0;
            for (Integer integer : linkedList) {
                if (i>0){
                    pMovieXstjMapper.deleteById(integer);
                    System.out.println("integer = " + integer);
                }
                i++;
            }
        }


//        String timestamp="550";
//        String url = "https://api.themoviedb.org/3/movie/" + timestamp + "?api_key=047cc1d00267ec4a18b7791675dc1566";
//        System.out.println(url);
//        String s = Methods.generalGet(url);
//        System.out.println(s);
//        List<SimUser> simUsers = simUsersMapper.selectList(null);
//        System.out.println(simUsers);
//        List<Movies> movies = moviesMapper.selectList(new LambdaQueryWrapper<Movies>().select(Movies::getGenres).groupBy(Movies::getGenres));
//        System.out.println(movies.size());
//        LinkedHashMap a=new LinkedHashMap();
//        for (Movies movie : movies) {
//            String genres = movie.getGenres();
//            String[] split = genres.split("\\|");
//            for (String s : split) {
//                a.put(s,s);
//            }
//        }
//        System.out.println(a.size());
//        String a="Documentary";
//        String b="Crime|Drama";
//        String[] asplit = a.split("\\|");
//        String[] bsplit = b.split("\\|");
//        for (String s : asplit) {
//            System.out.println(s);
//        }
//        for (String s : bsplit) {
//            System.out.println(s);
//        }
    }
}
