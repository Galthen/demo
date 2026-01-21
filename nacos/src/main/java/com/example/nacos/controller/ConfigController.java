package com.example.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${user.name:nacos_not_found}")
    private String userName;

    @Value("${user.age:0}")
    private Integer userAge;

    @GetMapping("/config/get")
    public String getConfig() {
        return "Nacos 配置 -> Name: " + userName + ", Age: " + userAge;
    }
}