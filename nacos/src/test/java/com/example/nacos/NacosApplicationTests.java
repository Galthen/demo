package com.example.nacos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NacosApplicationTests {

    @Autowired
    private Environment environment;

    @Test
    void testNacosConfigLoad() {
        String configValue = environment.getProperty("user.name");

        System.out.println("====== 测试开始 ======");
        System.out.println("读取到的 Nacos 配置值为: " + configValue);
        System.out.println("====== 测试结束 ======");

        assertNotNull(configValue, "未能从 Nacos 加载到配置，请检查 Data ID 或 Group 是否匹配！");
    }

}
