package com.epam.mysite.util.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import static com.epam.mysite.util.gson.UnixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter;

public class CustomGson {
    private CustomGson() {
    }

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, getUnixEpochDateTypeAdapter())
            .create();

    public static Gson gson() {
        return gson;
    }
}
