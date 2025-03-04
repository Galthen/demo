package com.example.demo.test;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

public class HutoolTest {

    @Test
    public void testRemoveSuffix() {
        String fileName = StrUtil.removeSuffix("pretty_girl.jpg", ".jpg");
        System.out.println(fileName);
    }
}
