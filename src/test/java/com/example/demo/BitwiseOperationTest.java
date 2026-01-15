package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testBasicPermissionAssignment() {
        // 初始没有任何权限
        int userAuth = 0;
        assertEquals(0, userAuth, "初始权限应为0");

        // 授予查询和插入权限: 0001 | 0010 = 0011 (十进制 3)
        userAuth = ALLOW_SELECT | ALLOW_INSERT;
        assertEquals(3, userAuth, "SELECT(1) + INSERT(2) = 3");
    }

    @Test
    public void testPermissionChecking() {
        // 创建一个拥有SELECT和UPDATE权限的用户
        int userAuth = ALLOW_SELECT | ALLOW_UPDATE; // 0001 | 0100 = 0101 (5)

        // 检查权限
        assertTrue((userAuth & ALLOW_SELECT) != 0, "应该拥有SELECT权限");
        assertTrue((userAuth & ALLOW_UPDATE) != 0, "应该拥有UPDATE权限");
        assertFalse((userAuth & ALLOW_INSERT) != 0, "不应该拥有INSERT权限");
        assertFalse((userAuth & ALLOW_DELETE) != 0, "不应该拥有DELETE权限");
    }

    @Test
    public void testAddPermission() {
        int userAuth = ALLOW_SELECT; // 初始只有SELECT权限

        // 添加INSERT权限
        userAuth = userAuth | ALLOW_INSERT; // 0001 | 0010 = 0011 (3)
        assertEquals(3, userAuth, "添加INSERT后权限值应为3");

        // 验证权限
        assertTrue((userAuth & ALLOW_SELECT) != 0);
        assertTrue((userAuth & ALLOW_INSERT) != 0);
    }

    @Test
    public void testRemovePermission() {
        // 初始有SELECT和INSERT权限
        int userAuth = ALLOW_SELECT | ALLOW_INSERT; // 0011 (3)

        // 移除INSERT权限
        userAuth = userAuth & ~ALLOW_INSERT; // 0011 & ~0010 = 0011 & 1101 = 0001 (1)
        assertEquals(1, userAuth, "移除INSERT后权限值应为1");

        // 验证权限
        assertTrue((userAuth & ALLOW_SELECT) != 0, "应该保留SELECT权限");
        assertFalse((userAuth & ALLOW_INSERT) != 0, "INSERT权限应该被移除");
    }

    @Test
    public void testTogglePermission() {
        int userAuth = ALLOW_SELECT; // 初始只有SELECT权限

        // 切换INSERT权限（添加）
        userAuth = userAuth ^ ALLOW_INSERT; // 0001 ^ 0010 = 0011 (3)
        assertEquals(3, userAuth, "第一次切换INSERT后权限值应为3");
        assertTrue((userAuth & ALLOW_INSERT) != 0, "现在应该有INSERT权限");

        // 再次切换INSERT权限（移除）
        userAuth = userAuth ^ ALLOW_INSERT; // 0011 ^ 0010 = 0001 (1)
        assertEquals(1, userAuth, "第二次切换INSERT后权限值应为1");
        assertFalse((userAuth & ALLOW_INSERT) != 0, "现在应该没有INSERT权限");
    }

    @Test
    public void testAllPermissions() {
        // 授予所有权限
        int adminAuth = ALLOW_SELECT | ALLOW_INSERT | ALLOW_UPDATE | ALLOW_DELETE;

        // 计算期望值：1 + 2 + 4 + 8 = 15
        assertEquals(15, adminAuth, "所有权限的值应为15");

        // 验证所有权限都存在
        assertTrue((adminAuth & ALLOW_SELECT) != 0);
        assertTrue((adminAuth & ALLOW_INSERT) != 0);
        assertTrue((adminAuth & ALLOW_UPDATE) != 0);
        assertTrue((adminAuth & ALLOW_DELETE) != 0);
    }

    @Test
    public void testNoPermissions() {
        int userAuth = 0;

        // 验证没有任何权限
        assertFalse((userAuth & ALLOW_SELECT) != 0);
        assertFalse((userAuth & ALLOW_INSERT) != 0);
        assertFalse((userAuth & ALLOW_UPDATE) != 0);
        assertFalse((userAuth & ALLOW_DELETE) != 0);
    }

    @Test
    public void testMultipleOperations() {
        int userAuth = 0;

        // 逐步添加权限
        userAuth |= ALLOW_SELECT;  // 添加SELECT
        userAuth |= ALLOW_UPDATE;  // 添加UPDATE

        assertEquals(5, userAuth, "SELECT(1) + UPDATE(4) = 5");
        assertTrue((userAuth & ALLOW_SELECT) != 0);
        assertTrue((userAuth & ALLOW_UPDATE) != 0);
        assertFalse((userAuth & ALLOW_INSERT) != 0);

        // 移除一个权限，添加另一个
        userAuth &= ~ALLOW_SELECT; // 移除SELECT
        userAuth |= ALLOW_INSERT;  // 添加INSERT

        assertEquals(6, userAuth, "UPDATE(4) + INSERT(2) = 6");
        assertFalse((userAuth & ALLOW_SELECT) != 0);
        assertTrue((userAuth & ALLOW_UPDATE) != 0);
        assertTrue((userAuth & ALLOW_INSERT) != 0);
    }

    @Test
    public void testPermissionCombinations() {
        // 测试各种权限组合
        int readOnly = ALLOW_SELECT;
        int writeOnly = ALLOW_INSERT | ALLOW_UPDATE | ALLOW_DELETE;
        int readWrite = ALLOW_SELECT | ALLOW_INSERT | ALLOW_UPDATE;

        assertEquals(1, readOnly, "只读权限值应为1");
        assertEquals(14, writeOnly, "只写权限值应为14 (2+4+8)");
        assertEquals(7, readWrite, "读写权限值应为7 (1+2+4)");

        // 验证组合权限
        assertTrue((readWrite & ALLOW_SELECT) != 0);
        assertTrue((readWrite & ALLOW_INSERT) != 0);
        assertTrue((readWrite & ALLOW_UPDATE) != 0);
        assertFalse((readWrite & ALLOW_DELETE) != 0);
    }

    @Test
    public void testEdgeCases() {
        // 测试重复添加同一权限
        int userAuth = ALLOW_SELECT;

        userAuth |= ALLOW_SELECT; // 重复添加SELECT
        assertEquals(1, userAuth, "重复添加同一权限不应改变值");

        // 测试移除不存在的权限
        userAuth &= ~ALLOW_INSERT; // 移除不存在的INSERT
        assertEquals(1, userAuth, "移除不存在的权限不应改变值");

        // 测试空操作
        userAuth = userAuth | 0;
        assertEquals(1, userAuth, "与0进行或操作不应改变值");

        // 测试与所有位进行与操作
        userAuth = userAuth & -1; // -1在二进制中是全1
        assertEquals(1, userAuth, "与全1进行与操作不应改变值");
    }
}