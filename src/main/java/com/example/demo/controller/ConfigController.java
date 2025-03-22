package com.example.demo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 测试 Nacos 配置
 *
 * @author chenyang
 * @date 2025/03/22
 */
@Controller
@RequestMapping("config")
public class ConfigController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @NacosValue(value = "${demo.test:777}", autoRefreshed = true)
    private String test;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        System.out.println("useLocalCache:" + useLocalCache);
        System.out.println("test:" + test);
        return useLocalCache;
    }
}