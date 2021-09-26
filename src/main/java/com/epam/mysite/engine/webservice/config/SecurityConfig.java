package com.epam.mysite.engine.webservice.config;

import com.epam.mysite.domain.enums.Role;

import java.util.*;

public class SecurityConfig {
    private SecurityConfig() {
    }

    private static final Map<Role, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        List<String> clientUrls = new ArrayList<>();
        clientUrls.add("/profile");
        clientUrls.add("/history");
        clientUrls.add("/logout");
        mapConfig.put(Role.Client, clientUrls);

        List<String> masterUrls = new ArrayList<>();
        masterUrls.add("/profile");
        masterUrls.add("/orders");
        masterUrls.add("/logout");
        mapConfig.put(Role.Master, masterUrls);

        List<String> adminUrls = new ArrayList<>();
        adminUrls.add("/profile");
        adminUrls.add("/orders");
        adminUrls.add("/admin");
        adminUrls.add("/logout");
        mapConfig.put(Role.Admin, adminUrls);
    }

    public static Set<Role> getRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlForRole(Role role) {
        return mapConfig.get(role);
    }
}
