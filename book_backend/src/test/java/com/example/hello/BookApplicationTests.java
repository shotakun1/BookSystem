package com.example.hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class BookApplicationTests {

    @Test
    void contextLoads() {
        String str = UUID.randomUUID().toString().replaceAll("-", "").substring(0,8);
        System.out.println(str);
    }

}
