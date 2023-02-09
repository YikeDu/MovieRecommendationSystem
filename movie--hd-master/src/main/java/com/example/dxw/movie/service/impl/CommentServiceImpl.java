package com.example.dxw.movie.service.impl;

import com.example.dxw.movie.pojo.Comment;
import com.example.dxw.movie.mapper.CommentMapper;
import com.example.dxw.movie.pojo.ResBean;
import com.example.dxw.movie.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper  commentMapper;
    @Override
    public Object insert(Map map) {
        Comment comment = new Comment();
        comment.setMid(map.get("mid").toString());
        comment.setCid(map.get("cid").toString());
        comment.setCTimeStr(map.get("ctimeStr").toString());
        comment.setCStr(map.get("cstr").toString());
        int insert = commentMapper.insert(comment);
        return ResBean.success("ok");
    }
}
