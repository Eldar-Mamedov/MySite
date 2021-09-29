package com.epam.mysite.util.converter;

import com.epam.mysite.util.HttpServletRequestHelper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

import static com.epam.mysite.util.IParser.fromJson;

public class RequestConverter {
    private RequestConverter() {
    }

    public static <T> T convertFromBody(HttpServletRequest request, Class<T> clazz) {
        String body = HttpServletRequestHelper.getBody(request);
        return fromJson(body, clazz);
    }

    public static <T> T convertFromBody(HttpServletRequest request, Type type) {
        String body = HttpServletRequestHelper.getBody(request);
        return fromJson(body, type);
    }
}
