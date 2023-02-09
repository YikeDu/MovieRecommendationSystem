package com.example.dxw.movie.service;

import com.example.dxw.movie.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface CommentService extends IService<Comment> {

    Object insert(Map map);
}
