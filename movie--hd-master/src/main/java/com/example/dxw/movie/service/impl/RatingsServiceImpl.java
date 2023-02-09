package com.example.dxw.movie.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dxw.https.General;
import com.example.dxw.https.Methods;
import com.example.dxw.movie.mapper.*;
import com.example.dxw.movie.pojo.*;
import com.example.dxw.movie.service.RatingsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dxw.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class RatingsServiceImpl extends ServiceImpl<RatingsMapper, Ratings> implements RatingsService {
    @Autowired
    private RatingsMapper ratingsMapper;
    @Autowired
    private MovieStarMapper movieStarMapper;
    @Autowired
    private MoviesMapper moviesMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SimUsersMapper simUsersMapper;
    @Autowired
    private RatingsNormMapper ratingsNormMapper;
    @Autowired
    private PMovieXstjMapper pMovieXstjMapper;
    @Autowired
    private PMovieMapper pMovieMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Object getdata(String type, String cid) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,cid));
        List<RcdItem> rcdItems = rcdFilm(user);// user or user id
        System.out.println(rcdItems);
        System.out.println("type = " + type);
        System.out.println("cid = " + cid);
        List<PMovieDO> pMovieDOS = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getFz,"5").last("limit 7"));
        List<PMovieDO> pMovieDOSrm = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getRouting,"rm").last("limit 7"));
        List<PMovieDO> pMovieDOStj = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getFz,"5").last("limit 7,7"));

        if (!cid.equals("null")){

            String xh = user.getXh();
            if (xh!=null){
                pMovieDOStj.clear();
                if (rcdItems.size()>0){
                    for (RcdItem rcdItem : rcdItems) {
                        String movieId = rcdItem.getMovieId()+"";
                        List<PMovieDO> pMovieDOS1 = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid, movieId));
                        for (PMovieDO pMovieDO : pMovieDOS1) {
                            pMovieDOStj.add(pMovieDO);
                        }
                    }
                }else {
                    String[] split = xh.split(",");
                    for (String s : split) {
                        List<Movies> movies = moviesMapper.selectList(new LambdaQueryWrapper<Movies>().like(Movies::getGenres,s));
                        for (Movies movie : movies) {
                            List<PMovieDO> pMovieDOS1 = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid, movie.getMovieid()));
                            for (PMovieDO pMovieDO : pMovieDOS1) {
                                pMovieDOStj.add(pMovieDO);
                            }
                        }
                    }
                }
                pMovieDOStj=pMovieDOStj.subList(0,7);
            }
        }


        pMovieDOStj=pMovieDOStj.subList(0,7);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        List<PMovieDO> gf = getcomments(pMovieDOS);
        List<PMovieDO> rm = getcomments(pMovieDOSrm);
        List<PMovieDO> tj = getcomments(pMovieDOStj);
        map.put("gf", gf);
        map.put("rm", rm);
        map.put("tj", tj);
        return ResBean.success("ok", map);
    }

//    public Object getdata1(String type, String cid) {
//        User user = new User();
//        user.setId(10);
//        rcdFilm(user);// user or user id
//        if (user != null) return null;
//
//        List<Object> linkedList = new LinkedList<>();
//        List<Object> linkedList3 = new LinkedList<>();
//        List<String> cxid = new LinkedList<>();
//        List<Ratings> ratings = ratingsMapper.selectList(new LambdaQueryWrapper<Ratings>().eq(Ratings::getRating, 5));
//        for (int i = 0; i < 160; i++) {
//            Ratings ratings1 = ratings.get(i);
//            String timestamp = ratings1.getMovieid();
//            cxid.add(timestamp);
//        }
//
//        for (int i = 0; i < cxid.size(); i++) {
//            String timestamp = cxid.get(i);
//            String url = "https://api.themoviedb.org/3/movie/" + timestamp + "?api_key=047cc1d00267ec4a18b7791675dc1566";
//            String url2 = "https://api.themoviedb.org/3/movie/" + timestamp + "/recommendations?api_key=047cc1d00267ec4a18b7791675dc1566&language=en-US&page=1";
//            String url3 = "https://api.themoviedb.org/3/movie/" + timestamp + "/similar?api_key=047cc1d00267ec4a18b7791675dc1566&language=en-US&page=1";
//
//            String s = "1";
//            String s2 = "1";
//            String s3 = "1";
//            String mid3 = "";
//            try {
//
//                s = Methods.generalGet(url);
//                s2 = Methods.generalGet(url2);
//                s3 = Methods.generalGet(url3);
//
//
//                mid3 = s2;
////                mid3= General.xs;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            String mid = s;
////            mid = General.mid;
//
//            String mid2 = s3;
////            mid2 = General.tj;
//
//            try {
//                List<Object> analysis = analysis(mid2);
//                List<Object> analysis2 = analysis(mid3);
//                Object image1 = JSONObject.parseObject(mid).get("backdrop_path");
//                Object image2 = JSONObject.parseObject(mid).get("poster_path");
//                Object voteCount = JSONObject.parseObject(mid).get("vote_count");
//                Object id = JSONObject.parseObject(mid).get("id");
//                Object runtime = JSONObject.parseObject(mid).get("runtime");
//                String imageSrc1 = "https://image.tmdb.org/t/p/w500" + image1;
//                String imageSrc2 = "https://image.tmdb.org/t/p/w500" + image2;
//                Object jj = JSONObject.parseObject(mid).get("overview");
//                Object language = JSONObject.parseObject(mid).get("original_language");
//                Object title = JSONObject.parseObject(mid).get("original_title");
//                String name = JSONObject.parseObject(mid).getJSONObject("belongs_to_collection").get("name").toString();
//                Object year = JSONObject.parseObject(mid).getJSONObject("belongs_to_collection").get("id");
//                JSONArray jsonArray = JSONObject.parseObject(mid).getJSONArray("genres");
//                List<Object> mtypeLis = new LinkedList<>();
//                for (Object o : jsonArray) {
//                    Object mtype = JSONObject.parseObject(o.toString()).get("name");
//                    mtypeLis.add(mtype);
//                }
//                LinkedHashMap<String, Object> map = new LinkedHashMap();
//                map.put("id", id);
//                map.put("movie", id);
//                map.put("year", year);
//                map.put("runtime", runtime);
//                map.put("imageSrc1", imageSrc2);
//                map.put("imageSrc2", imageSrc1);
//                map.put("jj", jj);
//                map.put("language", language);
//                map.put("title", title);
//
//                if (name.length() > 15) {
//                    name = name.substring(0, 15);
//                }
//                map.put("name", name);
//                map.put("mtypeLis", mtypeLis);
//                map.put("voteCount", voteCount);
//                map.put("tj", analysis);
//                map.put("xs", analysis2);
//                List<Comment> comment = getComent(id.toString());
//                map.put("comment", comment);
//                linkedList.add(map);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        List<Object> linkedList2 = new LinkedList<>();
////        for (int i = 0; i < linkedList.size(); i++) {
//        if (!type.equals("no")) {
//            for (int i = 0; i < 7; i++) {
//                linkedList2.add(linkedList.get(i));
//            }
//        } else {
//            int ii = 0;
//            for (Object o : linkedList) {
//                if (ii < linkedList.size()) {
//                    if (ii < 7) {
//                        linkedList2.add(o);
//                        ii++;
//                    }
//                }
//
//            }
//        }
//        String url = "https://api.themoviedb.org/3/trending/movie/day?api_key=047cc1d00267ec4a18b7791675dc1566";
//
//        String s = "1";
//        try {
//            s = Methods.generalGet(url);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String mid = s;
////        mid = General.day;
//
//
//        JSONArray jsonArray = JSONObject.parseObject(mid).getJSONArray("results");
//        for (Object o : jsonArray) {
//            String s1 = o.toString();
//            Object id = JSONObject.parseObject(s1).get("id");
//
//            try {
//                String url2 = "https://api.themoviedb.org/3/movie/" + id + "/similar?api_key=047cc1d00267ec4a18b7791675dc1566&language=en-US&page=1";
//                String s2 = Methods.generalGet(url2);
//                String mid2 = s2;
////                mid2 = General.tj;
//                String mid3 = General.xs;
//
//                List<Object> analysis = analysis(mid2);
//                List<Object> analysis2 = analysis(mid3);
//
//                Object image1 = JSONObject.parseObject(s1).get("backdrop_path");
//                Object image2 = JSONObject.parseObject(s1).get("poster_path");
//                Object voteCount = JSONObject.parseObject(s1).get("vote_count");
//
//                Object runtime = JSONObject.parseObject(s1).get("runtime");
//                String imageSrc1 = "https://image.tmdb.org/t/p/w500" + image1;
//                String imageSrc2 = "https://image.tmdb.org/t/p/w500" + image2;
//
//                Object jj = JSONObject.parseObject(s1).get("overview");
//                Object language = JSONObject.parseObject(s1).get("original_language");
//                Object title = JSONObject.parseObject(s1).get("original_title");
//                Object name = JSONObject.parseObject(s1).get("title");
//                Object year = JSONObject.parseObject(s1).get("release_date");
//                LinkedHashMap<String, Object> map = new LinkedHashMap();
//                map.put("id", id);
//                map.put("movie", id);
//                map.put("year", year);
//                map.put("runtime", runtime);
//                map.put("imageSrc1", imageSrc2);
//                map.put("imageSrc2", imageSrc1);
//                map.put("jj", jj);
//                map.put("language", language);
//                map.put("title", title);
//                map.put("name", name);
//                map.put("tj", analysis);
//                map.put("xs", analysis2);
//                List<Comment> comment = getComent(id.toString());
//                map.put("comment", comment);
////                map.put("mtypeLis", mtypeLis);
//                map.put("voteCount", voteCount);
//                linkedList3.add(map);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//        List<Object> linkedList5 = new LinkedList<>();
//        for (int i = 0; i < 7; i++) {
//            linkedList5.add(linkedList3.get(i));
//        }
//        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
//        map.put("gf", linkedList2);
//        map.put("gf", linkedList);
//        map.put("rm", linkedList5);
//        return ResBean.success("ok", map);
//    }

    @Override
    public Object like(Map map) {
        Object name = map.get("name");
        List<MovieStar> movieStars = movieStarMapper.selectList(new LambdaQueryWrapper<MovieStar>().eq(MovieStar::getCid, name));
        return ResBean.success("ok", movieStars);
    }

    @Override
    public Object getlb(String mid) {
        return ResBean.success("成功",getcomments2(mid));
    }

    @Override
    public Object getclassification(String type) {
        List<Movies> movies = moviesMapper.selectList(new LambdaQueryWrapper<Movies>().like(Movies::getGenres, type));
        List<PMovieDO> ys=new LinkedList<>();
        for (Movies movie : movies) {
            List<PMovieDO> pMovieDOS = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid,movie.getMovieid()));
            if (pMovieDOS.size()>0){
                ys.add(pMovieDOS.get(0));
            }
        }
        List<PMovieDO> linkedList = getcomments(ys);

        List linkedList2 = new LinkedList<>();
        int toIndex = 21;
        int flag = (int) Math.ceil((double) linkedList.size() / toIndex);
        if (linkedList.size() > 21) {
            for (int i = 0; i < flag; i++) {
                if (i * 21 + 21 > linkedList.size()) {
                    linkedList2.add(linkedList.subList(i * 21, linkedList.size() - 1));
                } else {
                    linkedList2.add(linkedList.subList(i + i * toIndex, (i + 1) * toIndex));
                }

            }
        }else {
            linkedList2=linkedList;
        }

        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("sj", linkedList2);
        map.put("cd", linkedList.size());
        return ResBean.success("ok", map);
    }
    public Object getclassification1(String type) {
        List<Object> linkedList = new LinkedList<>();
        List<Object> linkedList3 = new LinkedList<>();
        List<String> cxid = new LinkedList<>();
        List<Movies> movies = moviesMapper.selectList(new LambdaQueryWrapper<Movies>().like(Movies::getGenres, type));
        for (int i = 0; i < 160; i++) {
            String movieid = movies.get(i).getMovieid();
            cxid.add(movieid);
        }
        for (int i = 0; i < cxid.size(); i++) {
            String timestamp = cxid.get(i);
            String url = "https://api.themoviedb.org/3/movie/" + timestamp + "?api_key=047cc1d00267ec4a18b7791675dc1566";
            String url2 = "https://api.themoviedb.org/3/movie/" + timestamp + "/recommendations?api_key=047cc1d00267ec4a18b7791675dc1566&language=en-US&page=1";

            String s = "1";
            String s2 = "1";
            try {
                s = Methods.generalGet(url);
//                s2 = Methods.generalGet(url2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String mid = s;
            String mid2 = s2;
//            mid = General.mid;
            mid2 = General.tj;
            try {

                String urlxs = "https://api.themoviedb.org/3/movie/" + timestamp + "/recommendations?api_key=047cc1d00267ec4a18b7791675dc1566&language=en-US&page=1";
                String urltj = "https://api.themoviedb.org/3/movie/" + timestamp + "/similar?api_key=047cc1d00267ec4a18b7791675dc1566&language=en-US&page=1";

                urlxs = Methods.generalGet(urlxs);
                urltj = Methods.generalGet(urltj);
                List<Object> analysis = analysis(urltj);
                List<Object> analysis2 = analysis(urlxs);

                Object image1 = JSONObject.parseObject(mid).get("backdrop_path");
                Object image2 = JSONObject.parseObject(mid).get("poster_path");
                Object voteCount = JSONObject.parseObject(mid).get("vote_count");
                Object id = JSONObject.parseObject(mid).get("id");
                List<Comment> comment = getComent(id.toString());
                Object runtime = JSONObject.parseObject(mid).get("runtime");
                String imageSrc1 = "https://image.tmdb.org/t/p/w500" + image1;
                String imageSrc2 = "https://image.tmdb.org/t/p/w500" + image2;

                Object jj = JSONObject.parseObject(mid).get("overview");
                Object language = JSONObject.parseObject(mid).get("original_language");
                Object title = JSONObject.parseObject(mid).get("original_title");
                String name = JSONObject.parseObject(mid).getJSONObject("belongs_to_collection").get("name").toString();
                Object year = JSONObject.parseObject(mid).getJSONObject("belongs_to_collection").get("id");
                JSONArray jsonArray = JSONObject.parseObject(mid).getJSONArray("genres");
                List<Object> mtypeLis = new LinkedList<>();
                for (Object o : jsonArray) {
                    Object mtype = JSONObject.parseObject(o.toString()).get("name");
                    mtypeLis.add(mtype);
                }
                LinkedHashMap<String, Object> map = new LinkedHashMap();
                map.put("id", id);
                map.put("movie", id);
                map.put("year", year);
                map.put("runtime", runtime);
                map.put("imageSrc1", imageSrc2);
                map.put("imageSrc2", imageSrc1);
                map.put("jj", jj);
                map.put("language", language);
                map.put("title", title);
                map.put("tj", analysis);
                map.put("xs", analysis2);
                if (name.length() > 15) {
                    name = name.substring(0, 15);
                }
                map.put("name", name);
                map.put("mtypeLis", mtypeLis);
                map.put("voteCount", voteCount);
                map.put("comment", comment);
                linkedList.add(map);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        List<Object> linkedList2 = new LinkedList<>();
        int toIndex = 21;
        int flag = (int) Math.ceil((double) linkedList.size() / toIndex);
        if (linkedList.size() > 21) {
            for (int i = 0; i < flag; i++) {
                if (i * 21 + 21 > linkedList.size()) {
                    linkedList2.add(linkedList.subList(i * 21, linkedList.size() - 1));
                } else {
                    linkedList2.add(linkedList.subList(i + i * toIndex, (i + 1) * toIndex));
                }

            }
        }else {
            linkedList2=linkedList;
        }

        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("sj", linkedList2);
        map.put("cd", linkedList.size());
        return ResBean.success("ok", map);
    }

    public List<Object> analysis(String mid) {
        LinkedList<Object> objects = new LinkedList<>();
        JSONArray jsonArray = JSONObject.parseObject(mid).getJSONArray("results");
        for (Object o : jsonArray) {
            String s1 = o.toString();
            Object image1 = JSONObject.parseObject(s1).get("backdrop_path");
            Object image2 = JSONObject.parseObject(s1).get("poster_path");
            Object voteCount = JSONObject.parseObject(s1).get("vote_count");
            Object id = JSONObject.parseObject(s1).get("id");
//            Object runtime = JSONObject.parseObject(s1).get("runtime");
            String imageSrc1 = "https://image.tmdb.org/t/p/w500" + image1;
            String imageSrc2 = "https://image.tmdb.org/t/p/w500" + image2;

            Object jj = JSONObject.parseObject(s1).get("overview");
            Object language = JSONObject.parseObject(s1).get("original_language");
            String title = JSONObject.parseObject(s1).get("original_title").toString();
            if (title.length() > 36) {
                title = title.substring(0, 36) + "...";
            }
            String name = JSONObject.parseObject(s1).get("title").toString();
            if (name.length() > 15) {
                name = name.substring(0, 15);
            }
            Object year = JSONObject.parseObject(s1).get("release_date");
            LinkedHashMap<String, Object> map = new LinkedHashMap();
            map.put("id", id);
            map.put("movie", id);
            map.put("year", year);
            map.put("runtime", "runtime");
            map.put("imageSrc1", imageSrc2);
            map.put("imageSrc2", imageSrc1);
            map.put("jj", jj);
            map.put("language", language);
            map.put("title", title);
            map.put("name", name);
//                map.put("mtypeLis", mtypeLis);
            map.put("voteCount", voteCount);
            objects.add(map);
        }
        List<Object> objects1 = new LinkedList<>();
        if (objects.size() > 6) {
            objects1 = objects.subList(0, 7);
        } else {
            return objects;
        }
        return objects1;
    }

    public List<Comment> getComent(String movid) {
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getMid, movid));
        return comments;
    }

    // todo 个性化推荐，如果返回空，推荐用户选择的类型对应最热门电影
    public List<RcdItem> rcdFilm(User user) {

        if (user == null || user.getId() <= 0) return Collections.emptyList();

        List<RcdItem> rcdItems = new ArrayList<>();

        // 1.fetch sim users
        SimUser simUsers = simUsersMapper.selectOne(new LambdaQueryWrapper<SimUser>().eq(SimUser::getUserId, user.getId()));

        //System.out.println(simUsers);
        if (simUsers == null ||  simUsers.getSimUser() == null) return Collections.emptyList();

        String[] sims = simUsers.getSimUser().split(",");
        // <sim_user_id, sim_score>
        Map<Integer, Double> simUsersMap = new HashMap<>();
        for (String entry : sims) {
            String[] part = entry.split(":");
            int id = Integer.parseInt(part[0]);
            double score = Double.parseDouble(part[1]);
            simUsersMap.put(id, score);
        }

        // sort by sim_score
        List<Map.Entry<Integer, Double>> entryList = new ArrayList<>(simUsersMap.entrySet());
        entryList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        //System.out.println(entryList);// sim user

        int topN = Math.min(50, entryList.size());// topN of sim users
        entryList = entryList.subList(0, topN);

        // sim user id
        List<Integer> simUserIds = entryList.stream().map(Map.Entry::getKey).collect(Collectors.toList());
       // System.out.println(simUserIds);
        // fetch me ratings
        List<RatingsNorm> meRatings = ratingsNormMapper.selectList(new LambdaQueryWrapper<RatingsNorm>().eq(RatingsNorm::getUserId, user.getId()));
       // System.out.println(meRatings);
        // fetch sim user ratings

        List<RatingsNorm> ratings = new ArrayList<>();
            int sim = simUserIds.size();
            for (int i = 0; i < sim; i++) {
                int x = simUserIds.get(i);
                List<RatingsNorm> rating = ratingsNormMapper.selectList(new LambdaQueryWrapper<RatingsNorm>().eq(RatingsNorm::getUserId, x));
               // System.out.println(rating);
                ratings.addAll(rating);
                //System.out.println(ratings);
            }
//        List<RatingsNorm> ratings = ratingsNormMapper.selectList(new LambdaQueryWrapper<RatingsNorm>().eq(RatingsNorm::getUserId, simUserIds));
        //System.out.println("===================================");
       // System.out.println(ratings);
        // rcd
        Map<Integer, RcdItem> rcdItemMap = getRcdItem(meRatings, ratings, simUsersMap);
        //System.out.println(rcdItemMap);
        List<RcdItem> entries = new ArrayList<>(rcdItemMap.values());
        entries = entries.stream().sorted(Comparator.comparing(RcdItem::finalScore).reversed())
                .collect(Collectors.toList());

        for (RcdItem rcdItem : entries) {// rcd item
           System.out.println(rcdItem);
            rcdItems.add(rcdItem);
        }
        System.out.println(rcdItems);
        return rcdItems;
    }

    private Map<Integer, RcdItem> getRcdItem(List<RatingsNorm> meRatings, List<RatingsNorm> ratings, Map<Integer, Double> simUsersMap) {
        Map<Integer, RcdItem> ret = new HashMap<>();

        // movie_ids of me
        Set<Integer> mIds = new HashSet<>();
        for (RatingsNorm r : meRatings) {
            mIds.add(r.getMovieId());
        }

        for (RatingsNorm r : ratings) {
         // if (mIds.contains(r.getMovie_id())) continue;// 我看过，不要

            double score = r.getRating() * simUsersMap.get(r.getUserId());// rating * sim_score
            ret.computeIfAbsent(r.getMovieId(), RcdItem::new).inc(score);
        }

        return ret;
    }

    class RcdItem{
        private long movieId;
        private int count;
        private double score;
        private double fScore;
        public long getMovieId() {
            return movieId;
        }
        public RcdItem(long movieId) {
            this.movieId = movieId;
            this.count = 0;
            this.score = 0;
        }

        public void inc(double score) {
            this.count += 1;
            this.score += score;
        }

        public double finalScore() {
            fScore = this.score / this.count;
            return fScore;
        }

        @Override
        public String toString() {
            return "RcdItem{" +
                    "movieId=" + movieId +
                    ", fScore=" + fScore +
                    '}';
        }
    }
    public List<PMovieDO> getcomments(List<PMovieDO> pMovieDOS){
        for (PMovieDO pMovieDO : pMovieDOS) {
            String movieid = pMovieDO.getMovieid();
            List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getMid, movieid));
//            List<PMovieXstjDO> pMovieXstjDOS = pMovieXstjMapper.selectList(new LambdaQueryWrapper<PMovieXstjDO>().eq(PMovieXstjDO::getTj,movieid).last("limit 7"));
//            List<PMovieXstjDO> pMovieXstjDOS2 = pMovieXstjMapper.selectList(new LambdaQueryWrapper<PMovieXstjDO>().eq(PMovieXstjDO::getTj,movieid).orderByDesc(PMovieXstjDO::getId).last("limit 7"));
            pMovieDO.setComment(comments);
//            pMovieDO.setTj(pMovieXstjDOS);
//            pMovieDO.setXs(pMovieXstjDOS2);
        }
        return pMovieDOS;
    }
    public Object getcomments2( String movieid ){


            List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getMid, movieid));
            List<PMovieXstjDO> pMovieXstjDOS = pMovieXstjMapper.selectList(new LambdaQueryWrapper<PMovieXstjDO>().eq(PMovieXstjDO::getTj,movieid).last("limit 7"));
            List<PMovieXstjDO> pMovieXstjDOS2 = pMovieXstjMapper.selectList(new LambdaQueryWrapper<PMovieXstjDO>().eq(PMovieXstjDO::getTj,movieid).orderByDesc(PMovieXstjDO::getId).last("limit 7"));
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("tj",pMovieXstjDOS);
        map.put("pl",comments);
        map.put("xs",pMovieXstjDOS2);
        return map;
    }

}
