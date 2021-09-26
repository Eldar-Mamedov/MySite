package com.epam.mysite.util.converter;

import com.epam.mysite.util.HttpServletRequestHelper;

import javax.servlet.http.HttpServletRequest;

import static com.epam.mysite.util.IParser.fromJson;

public class RequestConverter {
    private RequestConverter() {
    }

    public static <T> T convertFromBody(HttpServletRequest request, Class<T> clazz) {
        String body = HttpServletRequestHelper.getBody(request);
        return fromJson(body, clazz);
    }
}
