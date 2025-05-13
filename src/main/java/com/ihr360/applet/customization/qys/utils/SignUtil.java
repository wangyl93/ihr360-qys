package com.ihr360.applet.customization.qys.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.text.SimpleDateFormat;

public class SignUtil {

    public static void main(String[] args) throws Exception {
        String requestUrl = "http://example.com/api?param1=value1&param2=value2";
        String secret = "ABC123";
        String signedUrl = generateSignedUrl(requestUrl, secret);
        System.out.println("Signed URL: " + signedUrl);
    }

    public static String generateSignedUrl(String requestUrl, String secret) throws Exception {
        URL url = new URL(requestUrl);
        Map<String, String> queryParams = getQueryParams(url.getQuery());
        StringBuilder str = new StringBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (!entry.getKey().equals("sign")) {
                if (str.length() > 0) {
                    str.append("&");
                }
                str.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }

        long noncestrn = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dtime = sdf.format(new Date());

        String signStr = str.toString();
        if (!signStr.isEmpty()) {
            signStr += "&";
        }
        signStr += "noncestr=" + noncestrn + "&dtime=" + dtime + "&secret=" + secret;

        String signUrl = url.getProtocol() + "://" + url.getHost() + url.getPath() + "?" + signStr;
        System.out.println("加密字符串 = " + signUrl);
        String signn = md5(signUrl);
        System.out.println("md5加密后sign = " + signn);

        queryParams.put("noncestr", String.valueOf(noncestrn));
        queryParams.put("dtime", dtime);
        queryParams.put("sign", signn);

        return url.getProtocol() + "://" + url.getHost() + url.getPath() + "?" + buildQueryString(queryParams);
    }

    private static Map<String, String> getQueryParams(String query) throws UnsupportedEncodingException {
        Map<String, String> queryParams = new LinkedHashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryParams.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return queryParams;
    }

    private static String buildQueryString(Map<String, String> queryParams) {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return queryString.toString();
    }

    private static String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
