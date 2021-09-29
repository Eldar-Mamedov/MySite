package com.epam.mysite.util;

import com.epam.mysite.util.gson.CustomGson;

import java.lang.reflect.Type;

public interface IParser {
    static <T> T fromJson(final String json, final Class<T> clazz) {
        return CustomGson.gson().fromJson(json, clazz);
    }

    static <T> T fromJson(final String json, final Type type) {
        return CustomGson.gson().fromJson(json, type);
    }

    static String toJson(final Object obj) {
        return CustomGson.gson().toJson(obj);
    }
}
