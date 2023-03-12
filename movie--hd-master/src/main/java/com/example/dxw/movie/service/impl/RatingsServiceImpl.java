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
import org.python.util.PythonInterpreter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.dxw.movie.service.impl.Pybat.run_cmd;


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
    @Autowired
    private LbMapper lbMapper;

    @Override
    public Object deletM(String id) {
        System.out.println("id = " + id);
        int i = movieStarMapper.deleteById(id);

        return null;
    }

    @Override
    public Object getxk(String cid, String mid) {

        MovieStar movieStars = movieStarMapper.selectOne(new LambdaQueryWrapper<MovieStar>()
                .eq(MovieStar::getCid, cid).eq(MovieStar::getMovie, mid));
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getMid, mid).orderByDesc(Comment::getId));
        LinkedHashMap<Object, Object> linkMap = new LinkedHashMap<>();
        linkMap.put("star", movieStars);
        linkMap.put("comments", comments);
        return ResBean.success("成功", linkMap);
    }

    @Override
    public Object getdata(String type, String cid) {
        run_cmd("python D:\\movie\\movie--hd-master\\ucf.py");
//        runPythonScript("\"D:\\\\movie\\\\movie--hd-master\\\\movie--hd-master\\\\ucf.py\"");
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, cid));
        List<RcdItem> rcdItems = rcdFilm(user);// user or user id
        System.out.println(rcdItems);
        System.out.println("type = " + type);
        System.out.println("cid = " + cid);
        String titlel = "Movies recommended based on genres you like";
        List<PMovieDO> pMovieDOS = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getFz, "5").last("limit 7"));

        List<PMovieDO> pMovieDOSrm = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getRouting, "rm").last("limit 7"));
        List<PMovieDO> pMovieDOStj = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getFz, "5").last("limit 7,7"));
        List<PMovieDO> pMovieDOStj22 =new ArrayList<>();
//
        if (!cid.equals("null")) {

            String xh = user.getXh();


            if (xh != null) {
                pMovieDOStj.clear();
                if (rcdItems.size() > 0) {
                    titlel = "Movies recommended by similar users to you";
                    for (RcdItem rcdItem : rcdItems) {
                        String movieId = rcdItem.getMovieId() + "";
                        List<PMovieDO> pMovieDOS1 = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid, movieId));
                        for (PMovieDO pMovieDO : pMovieDOS1) {
                            String title = pMovieDO.getTitle();
                            pMovieDOStj.add(pMovieDO);
                        }
                    }
                } else {
                    String[] split = xh.split(",");
                    for (String s : split) {
                        List<Movies> movies = moviesMapper.selectList(new LambdaQueryWrapper<Movies>().like(Movies::getGenres, s));
                        System.out.println("movies = " + movies.size());
                        for (Movies movie : movies) {
                            List<PMovieDO> pMovieDOS1 = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid, movie.getMovieid()));
                            for (PMovieDO pMovieDO : pMovieDOS1) {
                                String title = pMovieDO.getTitle();
                                pMovieDOStj.add(pMovieDO);
                            }
                        }
                    }
                }

                for (PMovieDO pMovieDO : pMovieDOStj) {
                    String movieid = pMovieDO.getMovieid();
                    MovieStar movieStar = movieStarMapper.selectOne(new LambdaQueryWrapper<MovieStar>()
                            .eq(MovieStar::getMovie,movieid).eq(MovieStar::getCid,cid).last("limit 1"));
                    if (movieStar==null){
                       // System.out.println("movieStar = =======================");
                        pMovieDOStj22.add(pMovieDO);
                    }
                }
                pMovieDOStj = pMovieDOStj22.subList(0, 7);
            }
        }


        pMovieDOStj = pMovieDOStj.subList(0, 7);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        List<PMovieDO> gf = getcomments(pMovieDOS);
        List<PMovieDO> rm = getcomments(pMovieDOSrm);
        List<PMovieDO> tj = getcomments(pMovieDOStj);
        map.put("gf", gf);
        map.put("rm", rm);
        map.put("tj", tj);
        map.put("title", titlel);
        return ResBean.success("ok", map);
    }

    @Override
    public Object like(Map map) {
        Object name = map.get("name");
        List<MovieStar> movieStars = movieStarMapper.selectList(new LambdaQueryWrapper<MovieStar>().eq(MovieStar::getCid, name));

        return ResBean.success("ok", movieStars);
    }

    @Override
    public Object getlb(String mid, String uid) {
        return ResBean.success("成功", getcomments2(mid, uid));
    }

    @Override
    public Object getclassification(String type) {
        List<Movies> movies = moviesMapper.selectList(new LambdaQueryWrapper<Movies>().like(Movies::getGenres, type));
        List<PMovieDO> ys = new LinkedList<>();
        for (Movies movie : movies) {
            List<PMovieDO> pMovieDOS = pMovieMapper.selectList(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid, movie.getMovieid()));
            if (pMovieDOS.size() > 0) {
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
        } else {
            linkedList2 = linkedList;
        }
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("sj", linkedList2);
        map.put("cd", linkedList.size());
        return ResBean.success("ok", map);
    }

//    public void runPythonScript(String scriptPath) {
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile(scriptPath);
//    }
//    public  void runPython(){
//            PythonInterpreter interpreter = new PythonInterpreter();
//            interpreter.execfile("D:\\movie\\movie--hd-master\\movie--hd-master\\ucf.py");
//
//    }


    public List<Comment> getComent(String movid) {
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getMid, movid));
        return comments;
    }

    // todo 个性化推荐，如果返回空，推荐用户选择的类型对应最热门电影
    public List<RcdItem> rcdFilm(User user) {
        List<RcdItem> entries22 = new ArrayList<>();

        if (user == null || user.getId() <= 0) return Collections.emptyList();

        List<RcdItem> rcdItems = new ArrayList<>();

        // 1.fetch sim users
        SimUser simUsers = simUsersMapper.selectOne(new LambdaQueryWrapper<SimUser>().eq(SimUser::getUserId, user.getId()));

        //System.out.println(simUsers);
        if (simUsers == null || simUsers.getSimUser() == null) return Collections.emptyList();
        if (simUsers.getSimUser() == null || simUsers.getSimUser().equals("")) {
            return entries22;
        }
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

        System.out.println(entryList);// sim user

        int topN = Math.min(50, entryList.size());// topN of sim users
        entryList = entryList.subList(0, topN);

        // sim user id
        List<Integer> simUserIds = entryList.stream().map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(simUserIds);
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
            //System.out.println(rcdItem);
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
            if (mIds.contains(r.getMovieId())) continue;// 我看过，不要

            double score = r.getRating() * simUsersMap.get(r.getUserId());// rating * sim_score
            ret.computeIfAbsent(r.getMovieId(), RcdItem::new).inc(score);
        }

        return ret;
    }

    class RcdItem {
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

    public List<PMovieDO> getcomments(List<PMovieDO> pMovieDOS) {
        for (PMovieDO pMovieDO : pMovieDOS) {
            String movieid = pMovieDO.getMovieid();
            List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getMid, movieid));
//            List<PMovieXstjDO> pMovieXstjDOS = pMovieXstjMapper.selectList(new LambdaQueryWrapper<PMovieXstjDO>().eq(PMovieXstjDO::getTj,movieid).last("limit 7"));
//            List<PMovieXstjDO> pMovieXstjDOS2 = pMovieXstjMapper.selectList(new LambdaQueryWrapper<PMovieXstjDO>().eq(PMovieXstjDO::getTj,movieid).orderByDesc(PMovieXstjDO::getId).last("limit 7"));

            String title = pMovieDO.getTitle();
            if (title.length() > 17) {
                pMovieDO.setTitle(title.substring(0, 17) + "...");
            }
            String mid = pMovieDO.getMovieid();
            String pj = "https://image.tmdb.org/t/p/w500";
            String imageSrc2 = pMovieDO.getImageSrc2();
            String imageSrc1 = pMovieDO.getImageSrc1();
            pMovieDO.setImageSrc1(pj + imageSrc2);
            pMovieDO.setImageSrc2(pj + imageSrc1);
            pMovieDO.setYear(mid);
            pMovieDO.setComment(comments);
            String[] mtypeLis = pMovieDO.getMtypeLis().split(",");
            String lb = "";
            for (String mtypeLi : mtypeLis) {
                LbDO lbDO = lbMapper.selectOne(new LambdaQueryWrapper<LbDO>().eq(LbDO::getLid, mtypeLi).last("limit 1"));
                if (lbDO != null) {
                    String name = lbDO.getName();
                    lb += name + ",";
                }
            }
            pMovieDO.setMtypeLis(lb);
            pMovieDO.setVoteCount("-");
//            pMovieDO.setTj(pMovieXstjDOS);
//            pMovieDO.setXs(pMovieXstjDOS2);
        }

        return pMovieDOS;
    }

    public Object getcomments2(String movieid, String uid) {
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getMid, movieid));
        PMovieDO pMovieDO = pMovieMapper.selectOne(new LambdaQueryWrapper<PMovieDO>().eq(PMovieDO::getMovieid, movieid).last("limit 1"));
        String[] xs = pMovieDO.getXs().split(",");
        String[] tj = pMovieDO.getTj().split(",");
        List<PMovieXstjDO> pMovieXstjDOS = new ArrayList<>();
        List<PMovieXstjDO> pMovieXstjDOS2 = new ArrayList<>();
        int j1 = 0;
        int j2 = 0;
        for (String x : xs) {
            PMovieXstjDO pMovieXstjDO = pMovieXstjMapper.selectOne(new LambdaQueryWrapper<PMovieXstjDO>().eq(PMovieXstjDO::getMovieid, x).last("limit 1"));
            if (pMovieXstjDO != null && j1 < 7) {
                String pj = "https://image.tmdb.org/t/p/w500";
                String imageSrc2 = pMovieXstjDO.getImageSrc2();
                String imageSrc1 = pMovieXstjDO.getImageSrc1();
                if (!imageSrc2.equals("") && !imageSrc1.equals("")) {
                    pMovieXstjDO.setImageSrc1(pj + imageSrc2);
                    pMovieXstjDO.setImageSrc2(pj + imageSrc1);
                    String[] mtypeLis = pMovieXstjDO.getMtypeLis().split(",");
                    String lb = "";
                    for (String mtypeLi : mtypeLis) {
                        LbDO lbDO = lbMapper.selectOne(new LambdaQueryWrapper<LbDO>().eq(LbDO::getLid, mtypeLi).last("limit 1"));
                        if (lbDO != null) {
                            String name = lbDO.getName();
                            lb += name + ",";
                        }
                    }
                    if (lb.equals("")) {
                        lb = "-";
                    }
                    pMovieXstjDO.setMtypeLis(lb);
                    String jj = pMovieXstjDO.getJj();
                    try {
                        String decode = URLDecoder.decode(jj, "UTF-8");
                        pMovieXstjDO.setJj(decode);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    pMovieXstjDOS.add(pMovieXstjDO);
                    j1++;
                }
            }
        }
        for (String x : tj) {
            PMovieXstjDO pMovieXstjDO = pMovieXstjMapper.selectOne(new LambdaQueryWrapper<PMovieXstjDO>().eq(PMovieXstjDO::getMovieid, x).last("limit 1"));
            if (pMovieXstjDO != null && j2 < 7) {
                String pj = "https://image.tmdb.org/t/p/w500";
                String imageSrc2 = pMovieXstjDO.getImageSrc2();
                String imageSrc1 = pMovieXstjDO.getImageSrc1();
                if (!imageSrc2.equals("") && !imageSrc1.equals("")) {
                    pMovieXstjDO.setImageSrc1(pj + imageSrc2);
                    pMovieXstjDO.setImageSrc2(pj + imageSrc1);
                    String[] mtypeLis = pMovieXstjDO.getMtypeLis().split(",");
                    String lb = "";
                    for (String mtypeLi : mtypeLis) {
                        LbDO lbDO = lbMapper.selectOne(new LambdaQueryWrapper<LbDO>().eq(LbDO::getLid, mtypeLi).last("limit 1"));
                        if (lbDO != null) {
                            String name = lbDO.getName();
                            lb += name + ",";
                        }
                    }
                    if (lb.equals("")) {
                        lb = "-";
                    }
                    pMovieXstjDO.setMtypeLis(lb);
                    pMovieXstjDOS2.add(pMovieXstjDO);
                    j2++;
                }
            }
        }


        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        List<MovieStar> movieStars = movieStarMapper.selectList(new LambdaQueryWrapper<MovieStar>()
                .eq(MovieStar::getMovie, movieid).eq(MovieStar::getCid, uid).orderByDesc(MovieStar::getId));
        String star = "0";
        if (movieStars.size() > 0) {
            MovieStar movieStar = movieStars.get(0);
            star = movieStar.getStar();
            System.out.println("movieStars = " + movieStar.getStar());
        }


        map.put("tj", title(pMovieXstjDOS));
        map.put("pl", comments);
        map.put("xs", title(pMovieXstjDOS2));
        map.put("star", star);
        return map;
    }

    public List<PMovieXstjDO> title(List<PMovieXstjDO> list) {
        for (PMovieXstjDO pMovieXstjDO : list) {
            String title = pMovieXstjDO.getTitle();
            String movieid = pMovieXstjDO.getMovieid();
            pMovieXstjDO.setYear(movieid);
            if (title.length() > 17) {
                pMovieXstjDO.setTitle(title.substring(0, 17) + "...");
            }
        }
        return list;
    }

}
