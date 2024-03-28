package com.example.hello.interceptors;

import com.example.hello.Utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();

        // 如果是OPTIONS请求，直接返回true，表示通过
        if ("OPTIONS".equals(method)) {
            return true;
        }

        String token = request.getHeader("Authorization");
        System.out.println(method);
        System.out.println(token);
        System.out.println("==========================");
        try{
            Map<String, Object> claims = JwtUtils.parseToken(token);
            String username = claims.get("username").toString();
//            System.out.println(username);

            request.setAttribute("username", username);
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
}
