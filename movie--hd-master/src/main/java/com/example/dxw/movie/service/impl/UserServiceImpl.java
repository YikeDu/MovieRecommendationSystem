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
        User users = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,cid));
        users.setXh(s);
        int i = userMapper.updateById(users);
        return ResBean.success("账号信息更新成功",i);
    }
}
