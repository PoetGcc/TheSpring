package com.gcc.thespring.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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

    /**
     * 获取接口上的泛型T
     *
     * @param o     接口
     * @param index 泛型索引
     */
    public static Class<?> getInterfaceT(Object o, int index) {
        Type[] types = o.getClass().getGenericInterfaces();
        if (types.length <= index) {
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType) types[index];
        Type type = parameterizedType.getActualTypeArguments()[index];
        return checkType(type, index);
    }

    /**
     * 获取类上的泛型T
     *
     * @param o     接口
     * @param index 泛型索引
     */
    public static Class<?> getClassT(Object o, int index) {
        Type type = o.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType parameterizedType) {
            Type actType = parameterizedType.getActualTypeArguments()[index];
            return checkType(actType, index);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType"
                    + ", but <" + type + "> is of type " + className);
        }
    }

    private static Class<?> checkType(Type type, int index) {
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType pt) {
            Type t = pt.getActualTypeArguments()[index];
            return checkType(t, index);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType"
                    + ", but <" + type + "> is of type " + className);
        }
    }


    public static void main(String[] args) {
        float a = 0.1f;
        float b = 0.2f;
        System.out.println((a + b) == 0.3f);

        Class<?> clz = getInterfaceT(new Response<String>() {
            @Override
            public void onSuccess(String data) {

            }
        }, 0);
        System.out.println(clz.getSimpleName());
    }

    public interface Response<T> {
        void onSuccess(T data);
    }
}
