package com.example.dxw.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dxw.movie.mapper.UserMapper;
import com.example.dxw.movie.pojo.ResBean;
import com.example.dxw.movie.pojo.User;
import com.example.dxw.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${server.port}")
    private String port;
    @Value("${file.address}")
    private String address;
    private String path = "/file";
    @Value("${ip}")
    private String ip;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/yyyy/MM/dd/");//线程安全
    public Object yzm(Map map) {
        Object zh = map.get("zh");
        Object mail = map.get("mail");
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername,zh).eq(User::getEmail,mail).last("limit 1"));
        if (user==null){
            return ResBean.error("The email account password is incorrect!");
        }else {
            String[] my={"0","1","2","3","4","5","6","7","8","9","A","a","B","b","C","c","D","d","E","e","F","f","G","g","H","h","I","i","J","j","K","k","L","l","M","m","N","n","O","o","P","p","Q","q","R","r","S","s","T","t","U","u","V","v","W","w","X","x","Y","y","Z","z"};
            int length = my.length;
            Random r = new Random();
            String jc="";
            for (int i1 = 0; i1 < 4; i1++) {
                int i = r.nextInt(length);
                String s1 = my[i];
                jc+=s1;
            }
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromEmail);
            simpleMailMessage.setTo(mail.toString());
            simpleMailMessage.setSubject("Cinematograph Verification Code: "+jc);
            simpleMailMessage.setText("Dear user,\n\n" +
                    "Your Cinematograph verification code is: " + jc + ".\n\n" +
                    "This code will be valid for 10 minutes, so please use it as soon as possible.\n\n" +
                    "If you did not request this code, please ignore this email.\n\n" +
                    "Thank you!\n\n" +
                    "The Cinematograph Team");
            javaMailSender.send(simpleMailMessage);
            user.setCode(jc);
            userMapper.updateById(user);
            return ResBean.success("The verification code is sent successfully. Procedure!");
        }
    }

    @Override
    public Object xgma(Map map) {
        Object zh = map.get("zh");
        Object mail = map.get("mail");
        Object yzm = map.get("yzm");
        Object ma = map.get("ma");
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername,zh).eq(User::getEmail,mail).eq(User::getCode,yzm).last("limit 1"));
        if (user==null){
            return ResBean.error("The email account password is incorrect!");
        }else {
            user.setPassword(ma.toString());
            int i = userMapper.updateById(user);
            return ResBean.success("账号信息更新成功",i);
        }
    }

    @Override
    public Object login(Object username, Object password) {
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUsername,username).eq(User::getPassword,password));
        if (users.size()>0){
            User user = users.get(0);
            String picturel = user.getPicturel();
            if (picturel==null){
                user.setPicturel("data:image/jpeg;base64,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");
            }else {
                user.setPicturel("http://" + ip + ":" + port + picturel);
            }
            return ResBean.success("ok",user);
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
        System.out.println("map = " + map);
        String id = map.get("id").toString();
        String oPassword = map.get("oPassword").toString();
        String Password = map.get("Password").toString();
        User users = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId,id));
        if(!users.getPassword().equals(oPassword)){
            return ResBean.error("与原密码不符合,修改失败!");
        }
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
        User users = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId,cid));
        users.setXh(s);
        int i = userMapper.updateById(users);
        return ResBean.success("账号信息更新成功",i);
    }
}
