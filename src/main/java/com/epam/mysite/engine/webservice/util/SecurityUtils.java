package com.epam.mysite.engine.webservice.util;

import com.epam.mysite.domain.enums.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

import static com.epam.mysite.engine.webservice.config.SecurityConfig.getRoles;
import static com.epam.mysite.engine.webservice.config.SecurityConfig.getUrlForRole;
import static com.epam.mysite.engine.webservice.util.UrlPatternUtils.getUrlPattern;

public class SecurityUtils {
    private SecurityUtils() {
    }

    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPatter = getUrlPattern(request);

        Set<Role> roles = getRoles();

        for (Role role : roles) {
            List<String> urlPatterns = getUrlForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPatter)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);

        Set<Role> roles = getRoles();

        for (Role role : roles) {
            if (!request.isUserInRole(role.name())) {
                continue;
            }
            List<String> urlPatterns = getUrlForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
