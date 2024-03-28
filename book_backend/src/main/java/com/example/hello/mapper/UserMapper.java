package com.example.hello.mapper;

import com.example.hello.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM users WHERE username=#{username} AND password=#{password}")
    User login(User user);

    @Insert("INSERT INTO users(username, password) VALUES (#{username},#{password})")
    void register(User user) throws Exception;

    @Select("SELECT * FROM users WHERE username=#{username}")
    User getUserInfo(String username);
}
