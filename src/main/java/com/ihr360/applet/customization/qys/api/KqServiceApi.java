package com.ihr360.applet.customization.qys.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ihr360.applet.customization.qys.consts.Constants;
import com.ihr360.applet.customization.qys.model.thirdpart.response.Check;
import com.ihr360.applet.customization.qys.model.thirdpart.response.KqUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class KqServiceApi {

    @Resource
    private WebClient ihrTarget;

    @Value("${thirdpart.api.url}")
    private String thirdpart_url;

    @Value("${thirdpart.api.secret}")
    private String apiKey;

    public List<Check> getCheckList(Map<Integer,String> userIdMap, String startDate, String endDate) {

        //Map<Integer, String> userMap = getUserMap(kqUserList);
        String url = "User/CheckList";
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("apiKey", apiKey);
        requestParams.add("userid", "0"); // 默认为0，表示所有用户
        requestParams.add("startDate", startDate);
        requestParams.add("endDate", endDate);

        List<Check> resultList = ihrTarget.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParams(requestParams)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Check>>() {})
                .block();
        for (Check check : resultList){
            // 原始字符串（注意Java中的转义）
            String checkTimeStr = check.getCheckTime();
            // 提取数字部分并转换为long
            String numStr = checkTimeStr.replaceAll("[^0-9]", "");
            long timestamp = Long.parseLong(numStr);
            // method 1: 转换为Date对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(new Date(timestamp));
            check.setCheckTime(formattedDate);
            //根据userid获取对应的employeeNo
            String employeeNo = userIdMap.get(Integer.parseInt(check.getUserID()));
            check.setUserID(employeeNo);

        }

        return resultList;
    }

    public String getAllDeviceList() {
        String url = "FKDevice/DeviceList";
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("apiKey", apiKey);
        requestParams.add("dev_id", ""); // 默认为空，表示所有设备

        String result;
        result = ihrTarget.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParams(requestParams)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return result;
    }

    public Map<String, String> getDeviceAddressMap() {

        Map<String, String> deviceMap = new HashMap<>();
        String object = getAllDeviceList();
        JSONArray resultArray = JSONObject.parseArray(object);
        for (Object o : resultArray) {
            JSONObject resultObject = (JSONObject) o;
            String deviceId = resultObject.getString("device_id");
            String Location = resultObject.getString("Location");
            deviceMap.put(deviceId, Location);
        }
        log.info("deviceMap: {}", deviceMap.size());
        return deviceMap;

    }

    public List<KqUser> getUserList(){
        String url = "User/Index";
        int pageNo = 1;
        List<KqUser> kqUserList = new ArrayList<>();

        boolean hasMore = true;
        while(hasMore){
            MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
            requestParams.add("apiKey", apiKey);
            requestParams.add("pageNo", pageNo+""); // 默认为1，表示第一页
            requestParams.add("pageSize", Constants.PAGE_SIZE1000+"");
            String response = ihrTarget.post()
                    .uri(uriBuilder -> uriBuilder
                            .path(url)
                            .queryParams(requestParams)
                            .build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue("")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JSONObject jsonObject = JSONObject.parseObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            //example {"succ":true,"msg":"","data":{"totalCount":3832,"list":[]}}
            int totalCount = data.getIntValue("totalCount");
            int currentCount = pageNo * Constants.PAGE_SIZE1000;
            if(currentCount >= totalCount){
                hasMore = false;
            }

            JSONArray dataArray = data.getJSONArray("list");
            List<KqUser> kqUsers = dataArray.toJavaList(KqUser.class);
            kqUserList.addAll(kqUsers);
            pageNo = pageNo + 1;
        }

        return kqUserList;
    }



}
