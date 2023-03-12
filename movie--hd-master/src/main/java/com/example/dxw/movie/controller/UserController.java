package com.example.dxw.movie.controller;


import com.example.dxw.movie.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserService  userService;
    @ApiOperation(value = "post登录")
    @PostMapping("user/login")/**///postmb注册
    public Object login(@RequestBody Map map) throws Exception {
        Object username = map.get("username");
        Object password = map.get("password");
        Object login = userService.login(username, password);
//        System.out.println("login = " + login);
        return login;
    }
    @ApiOperation(value = "postmb注册")
    @PostMapping("user/checkUsername")/**///postmb注册
    public Object checkUsername(@RequestBody Map map) throws Exception {
        Object username = map.get("username");
        Object password = map.get("password");
        Object email = map.get("email");
        System.out.println("map = " + map);
        return userService.checkUsername(username, password,email);
    }
    @ApiOperation(value = "修改账号信息")
    @PostMapping("user/UpdateU")/**///postmb注册
    public Object UpdateU(@RequestBody Map map) throws Exception {
        return userService.UpdateU(map);
    }
    @ApiOperation(value = "重置密码信息")
    @PostMapping("user/UpdateP")/**///postmb注册
    public Object UpdateP(@RequestBody Map map) throws Exception {
        return userService.UpdateP(map);
    }
    @ApiOperation(value = "注销账号")
    @PostMapping("user/deleteU")/**///postmb注册
    public Object deleteU(@RequestBody Map map) throws Exception {
        return userService.deleteU(map);
    }
    @ApiOperation(value = "喜好设置")
    @PostMapping("user/xh")/**///postmb注册
    public Object xh(@RequestBody Map map) throws Exception {
        System.out.println("map = " + map);
        return userService.xh(map);
    }

    @ApiOperation(value = "发送验证码")
    @PostMapping("user/yzm")/**///postmb注册
    public Object yzm(@RequestBody Map map) throws Exception {
        System.out.println("map = " + map);

        return userService.yzm(map);
    }
    @ApiOperation(value = "发送验证码修改密码")
    @PostMapping("user/xgma")/**///postmb注册
    public Object xgma(@RequestBody Map map) throws Exception {
        System.out.println("map = " + map);
        return userService.xgma(map);
    }
}

