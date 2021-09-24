package com.epam.mysite.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public interface IParser {
    static <T> T fromJson(final String json, final Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    static <T> T fromJson(final String json, final Type type) {
        return new Gson().fromJson(json, type);
    }

    static String toJson(final Object obj) {
        return new Gson().toJson(obj);
    }
}
