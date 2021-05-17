package com.ddareung2.server.common.util;

import com.ddareung2.server.common.Constants;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class CookieUtil {

    public Cookie createCookie(String key, String value) {
        Cookie token = new Cookie(key, value);

        token.setHttpOnly(true);
        token.setSecure(true);
        token.setMaxAge((int) Constants.ACCESS_TOKEN_VALIDITY);
        token.setPath("/");

        return token;
    }
}
