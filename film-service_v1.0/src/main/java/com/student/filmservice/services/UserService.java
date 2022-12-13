package com.student.filmservice.services;

import com.student.filmservice.entity.User;
import com.student.filmservice.common.ResultJson;
import com.student.filmservice.repository.UserRepository;
import com.student.filmservice.vo.LoginForm;
import com.student.filmservice.vo.SettingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResultJson signup(User user) {
        userRepository.save(user);

        return ResultJson.success();
    }

    public ResultJson checkUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return ResultJson.error();
        }

        return ResultJson.success();
    }

    public ResultJson checkEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return ResultJson.error();
        }

        return ResultJson.success();
    }

    public ResultJson login(LoginForm loginForm) {
        User user = userRepository.findByUsername(loginForm.getUsername());
        if (user == null) {
            return ResultJson.error("username not found");
        }

        if (!user.getPassword().equals(loginForm.getPassword())) {
            return ResultJson.error("password is incorrect");
        }

        return ResultJson.success(user);
    }

    public ResultJson setting(SettingForm settingForm) {
        User user = userRepository.findById(settingForm.getId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        user.setEmail(settingForm.getEmail());
        user.setPassword(settingForm.getPassword());
        userRepository.save(user);

        return ResultJson.success(user);
    }
}
