package com.example.dxw.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dxw.movie.mapper.UserMapper;
import com.example.dxw.movie.pojo.ResBean;
import com.example.dxw.movie.pojo.User;
import com.example.dxw.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Object login(Object username, Object password) {
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUsername,username).eq(User::getPassword,password));
        if (users.size()>0){
            return ResBean.success("ok",users.get(0));
        }
        return ResBean.error("不ok");
    }

    @Override
    public Object checkUsername(Object username, Object password, Object email) {
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (users.size()>0){
            return ResBean.error("你注册的账号已经存在!");
        }else {
            User user = new User();
            user.setUsername(username.toString());
            user.setPassword(password.toString());
            user.setEmail(email.toString());
            int insert = userMapper.insert(user);
            return ResBean.success("注册成功!");
        }
    }

    @Override
    public Object UpdateU(Map map) {
        String id = map.get("id").toString();
        String Email = map.get("Email").toString();
        String UserName = map.get("UserName").toString();
        User users = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId,id));
        users.setEmail(Email);
        users.setUsername(UserName);
        int i = userMapper.updateById(users);
        return ResBean.success("账号信息更新成功",i);
    }

    @Override
    public Object UpdateP(Map map) {
        String id = map.get("id").toString();
        String Password = map.get("Password").toString();
        User users = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId,id));
        users.setPassword(Password);
        int i = userMapper.updateById(users);
        return ResBean.success("账号信息更新成功",i);
    }

    @Override
    public Object deleteU(Map map) {
        String id = map.get("id").toString();
        return ResBean.success("账号信息更新成功",userMapper.deleteById(id));
    }

    @Override
    public Object xh(Map map) {
        Object cid = map.get("cid");
        ArrayList xh = (ArrayList) map.get("xh");
        String s="";
        for (int i = 0; i < xh.size(); i++) {
            Object o = xh.get(i);
            if (i==xh.size()-1){
                s=s+o;
            }else {
                s=s+o+",";
            }

        }
        User users = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,cid));
        users.setXh(s);
        int i = userMapper.updateById(users);
        return ResBean.success("账号信息更新成功",i);
    }
}
