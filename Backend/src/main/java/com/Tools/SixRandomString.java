package com.Tools;

import java.util.Random;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/5/7 下午1:30
 * @ProjectName：Backend
 * @Description：
 */
public class SixRandomString {
    // 生成随机的 6 位数字符串
    public static String generateRandomString() {
        Random random = new Random();
        // 定义字符串的最小值和最大值
        int minValue = 100000; // 6 位数的最小值
        int maxValue = 999999; // 6 位数的最大值

        // 生成一个在[minValue, maxValue]范围内的随机整数
        int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;

        // 将随机整数转换为字符串形式
        String randomString = Integer.toString(randomNumber);

        return randomString;
    }
}
