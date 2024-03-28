package com.example.hello.service;


import com.example.hello.Utils.JwtUtils;
import com.example.hello.mapper.UserMapper;
import com.example.hello.pojo.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private HttpServletRequest request;
    public String login(User user) {
        User userGet = userMapper.login(user);
        if (userGet != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            claims.put("password", user.getPassword());

            return JwtUtils.generateToken(claims);
        }else {
            return null;
        }
    }

    public boolean register(User user) {
        try {
            userMapper.register(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public User getUserInfo(){
        String username = (String) request.getAttribute("username");
        return userMapper.getUserInfo(username);
    }
}
