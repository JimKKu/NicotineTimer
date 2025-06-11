package com.jim.itimerserver.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class HttpUtils {


    private static final String u1 = "JimzzZ";
    private static final String p1 = "ZzzmiJ";

    public static boolean checkUPHeader(HttpHeaders headers,String u1,String p1) {
        List<String> uList = headers.get("username");
        List<String> pList = headers.get("password");

        if(CollectionUtils.isEmpty(uList) || CollectionUtils.isEmpty(pList)) {
            return false;
        }

        String u2 = uList.get(0);
        String p2 = pList.get(0);

        return u1.equals(u2) && p2.equals(p1);

    }


    public static boolean checkUPHeader(HttpHeaders headers) {
        List<String> uList = headers.get("username");
        List<String> pList = headers.get("password");

        if(CollectionUtils.isEmpty(uList) || CollectionUtils.isEmpty(pList)) {
            return false;
        }

        String u2 = uList.get(0);
        String p2 = pList.get(0);

        return u1.equals(u2) && p1.equals(p2);

    }
}
