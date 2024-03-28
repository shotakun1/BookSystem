package com.example.hello.controller;

import com.example.hello.Utils.Result;
import com.example.hello.pojo.User;
import com.example.hello.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String token = userService.login(user);
        if (token!=null) {
            return Result.success(token);
        } else
            return Result.error("用户名或密码错误");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (userService.register(user))
            return Result.success();
        else
            return Result.error("用户名已被使用");
    }

    @GetMapping("/user_info")
    public Result getUserInfo() {
        return Result.success(userService.getUserInfo());
    }
}
