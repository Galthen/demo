package com.example.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Month;
import org.junit.jupiter.api.Test;

/**
 * 测试 Hutool
 *
 * @author chenyang
 * @date 2025/03/22
 */
public class HutoolTest {

    @Test
    public void test1() {
        // 生肖
        String chineseZodiac = DateUtil.getChineseZodiac(1990);
        System.out.println(chineseZodiac);

        // 星座
        String zodiac = DateUtil.getZodiac(Month.NOVEMBER.getValue(), 15);
        System.out.println(zodiac);
    }
}
