package com.ihr360.applet.customization.qys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenUtils {

    private static Logger log = LoggerFactory.getLogger(TokenUtils.class);

    private static final String getTokenUrl = "https://openapi.ihr360.com/openapi/oauth/token?grant_type=client_credentials&scope=client";


    /**
     * 获取Token
     * @return
     */
    public static String getHrToken(String AppID, String AppSecret){
        String token = "";
        try {
            String id = AppID+":"+AppSecret;
            String encoderToken = Base64Utility.encoder(id);
            String returnVal = HttpUtils.sendPost(getTokenUrl,null,"Basic "+encoderToken);
            JSONObject jsonObject = JSON.parseObject(returnVal);
            token = StringUtils.toString(jsonObject.get("access_token"));
            log.info("token:"+token);
        }catch (Exception e){
            log.info("获取Base64编码token出错："+e.getMessage(),e);
        }
        return token;
    }

    public static String getHrToken(String url, String AppID, String AppSecret){
        String token = "";
        try {
            String id = AppID+":"+AppSecret;
            String encoderToken = Base64Utility.encoder(id);
            String returnVal = HttpUtils.sendPost(url,null,"Basic "+encoderToken);
            JSONObject jsonObject = JSON.parseObject(returnVal);
            token = StringUtils.toString(jsonObject.get("access_token"));
            log.info("token:"+token);
        }catch (Exception e){
            log.info("获取Base64编码token出错："+e.getMessage(),e);
        }
        return token;
    }
}
