package com.student.filmservice.controllers;

import com.student.filmservice.entity.User;
import com.student.filmservice.common.ResultJson;
import com.student.filmservice.services.UserService;
import com.student.filmservice.vo.LoginForm;
import com.student.filmservice.vo.SettingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @RequestMapping("/login")
    public ResultJson login(@Valid @RequestBody LoginForm loginForm) {
        return userService.login(loginForm);
    }

    @GetMapping
    @RequestMapping("/checkUsername")
    public ResultJson checkUsername(@RequestParam(value = "username") String username) {
        return userService.checkUsername(username);
    }

    @GetMapping
    @RequestMapping("/checkEmail")
    public ResultJson checkEmail(@RequestParam(value = "email") String email) {
        return userService.checkEmail(email);
    }

    @PostMapping
    @RequestMapping("/signup")
    public ResultJson signup(@Valid @RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping
    @RequestMapping("/setting")
    public ResultJson setting(@Valid @RequestBody SettingForm settingForm) {
        return userService.setting(settingForm);
    }
}
