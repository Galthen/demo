package com.example.mybaits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mybaits.mapper")
public class MybaitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybaitsApplication.class, args);
    }

}
