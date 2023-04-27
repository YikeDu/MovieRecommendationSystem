package com.example.dxw.movie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dxw.movie.pojo.User;

import java.util.Map;


public interface UserService extends IService<User> {

    Object login(Object username, Object password);

    Object checkUsername(Object username, Object password, Object email);

    Object UpdateU(Map map);

    Object UpdateP(Map map);

    Object deleteU(Map map);

    Object xh(Map map);
    Object yzm(Map map);

    Object xgma(Map map);
}
