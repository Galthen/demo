package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    @Test
    public void test() {
        class CollisionKey {
            @Override
            public int hashCode() {
                return 1;
            }
        }

        Map<Object, Object> map = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            map.put(new CollisionKey(), "val" + i);
        }

    }
}
