package com.emall.utils;

import java.util.Random;

/**
 * Created by kimvra on 2018/12/26
 */
public class KeyUtil {
    public static String generateId() {
        Random random = new Random();
        Integer id = random.nextInt(9000000) + 100000;

        return System.currentTimeMillis() + String.valueOf(id);
    }
}
