package com.epam.mysite.util;

import com.epam.mysite.domain.webservice.Response;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.epam.mysite.util.IParser.toJson;

public class HttpServletRequestHelper {
    private HttpServletRequestHelper() {
    }

    public static String getBody(HttpServletRequest request) {
        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = reader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    @SneakyThrows
    public static void sendResponse(HttpServletResponse httpServletResponse, Response response) {
        httpServletResponse.setStatus(response.getStatus());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.print(toJson(response));
        out.flush();
    }
}
