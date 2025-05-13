package com.ihr360.applet.customization.qys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ihr360.applet.customization.qys.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiTestController {

    public static void main(String[] args) {
        ApiTestController test = new ApiTestController();
        //开通openapi功能包后可以获得下面两个参数
        String appId = "531b3fdd-e9b5-4a01-9cec-de8258fb7e0d";
        String appSecret = "c1021a7a-16cc-4bf5-b865-d382f3baae05";
        //获取token
        String tokenStringResult = test.getIhrAccessToken(appId, appSecret);
        JSONObject jsonObject = JSON.parseObject(tokenStringResult);
        String token = jsonObject.getString("access_token");
        System.out.println("token = " + token);
        //调用get接口的示例
        String getApiStringResult = test.testGetApi("ENABLE", token);
        System.out.println("getApiStringResult get organizations= " + getApiStringResult);
        //调用post接口的示例
        List departmentIds = new ArrayList();
        departmentIds.add(0);
        departmentIds.add(3);
        String postApiStringResult = test.testPostApi(departmentIds, token);
        System.out.println("postApiStringResult get staffs= " + postApiStringResult);
    }

    /**
     * 获取token代码
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public String getIhrAccessToken (String appId, String appSecret) {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://openapi.ihr360.com/openapi/oauth/token?grant_type=client_credentials&scope=client");
        String result = null;

        //把appId和appSecret加工得到authorization
        String key = appId + ":" + appSecret;
        String authorization = "Basic " + Base64.encodeBase64String(key.getBytes());
        //设置请求头
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8;");
        httpPost.setHeader("Authorization", authorization);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity, "UTF-8");
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * get方法，获取部门清单
     *
     * @param departmentStatus
     * @param token
     * @return
     */
    public String testGetApi(String departmentStatus, String token) {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        String result = null;
        String requestUrl = "https://openapi.ihr360.com/openapi/thirdparty/api/org/v1/companies/organizations/basic";
        if (StringUtils.isNotBlank(departmentStatus)) {
            requestUrl += "?departmentStatus=" + departmentStatus;
        }
        // 设置header
        HttpGet httpGet = new HttpGet(requestUrl);
        String authorization = "Bearer " + token;
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8;");
        httpGet.setHeader("Authorization", authorization);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity, "UTF-8");
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            try {;
                response.close();
                httpClient.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * post方法，根据部门ID获取员工基本信息
     *
     * @param departmentIds
     * @param token
     * @return
     */
    public String testPostApi(List departmentIds, String token) {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        String result = null;
        HttpPost httpPost = new HttpPost("https://openapi.ihr360.com/openapi/thirdparty/api/staff/v1/department/staff/basic");
        // 设置header
        String authorization = "Bearer " + token;
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8;");
        httpPost.setHeader("Authorization", authorization);
        CloseableHttpResponse response = null;
        try {
            String departmentIdsStr = departmentIds.toString();
            httpPost.setEntity(new StringEntity(departmentIdsStr,"UTF-8"));
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity, "UTF-8");
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            try {;
                response.close();
                httpClient.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
