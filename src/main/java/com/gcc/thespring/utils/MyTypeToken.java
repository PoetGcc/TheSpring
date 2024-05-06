package com.gcc.thespring.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: gcc
 * @date: 2024/3/21 16:47
 */
public  class MyTypeToken<T> {
    private Type type;
    private  Type parameterizedType;
    public MyTypeToken() {
        getClassT(this, 0);
    }

    /**
     * 获取类上的泛型T
     *
     * @param o     接口
     * @param index 泛型索引
     */
    public  Class<?> getClassT(Object o, int index) {
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

    private  Class<?> checkType(Type type, int index) {
        if (type instanceof Class<?>) {
            this.type = type;
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Type t = pt.getActualTypeArguments()[index];
            parameterizedType = pt;
            return checkType(t, index);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType"
                    + ", but <" + type + "> is of type " + className);
        }
    }

    public Type getType() {
        return type;
    }

    public Type getParameterizedType() {
        return parameterizedType;
    }
}

