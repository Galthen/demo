package com.example.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Month;
import org.junit.jupiter.api.Test;

public class HutoolTest {

    @Test
    public void testZodiac() {
        // 生肖
        String chineseZodiac = DateUtil.getChineseZodiac(1990);
        System.out.println(chineseZodiac);

        // 星座
        String zodiac = DateUtil.getZodiac(Month.OCTOBER.getValue(), 15);
        System.out.println(zodiac);
    }
}
