package com.example.demo;

import org.junit.jupiter.api.Test;

public class BitwiseOperationTest {

    // 1 << 0 = 1  (二进制: 0001)
    public static final int ALLOW_SELECT = 1 << 0;

    // 1 << 1 = 2  (二进制: 0010)
    public static final int ALLOW_INSERT = 1 << 1;

    // 1 << 2 = 4  (二进制: 0100)
    public static final int ALLOW_UPDATE = 1 << 2;

    // 1 << 3 = 8  (二进制: 1000)
    public static final int ALLOW_DELETE = 1 << 3;

    @Test
    public void test() {
        class CollisionKey {
            @Override
            public int hashCode() {
                return 1;
            }
        }

        // 初始没有任何权限
        int userAuth = 0;

        // 授予查询和插入权限: 0001 | 0010 = 0011 (十进制 3)
        userAuth = ALLOW_SELECT | ALLOW_INSERT;
    }
}
