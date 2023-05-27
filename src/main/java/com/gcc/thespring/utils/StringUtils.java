package com.gcc.thespring.utils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String errorJson() {
        return "{\n" +
                "\t\"ret\": 900,\n" +
                "\t\"msg\": \"something is error\"\n" +
                "}";
    }
}
