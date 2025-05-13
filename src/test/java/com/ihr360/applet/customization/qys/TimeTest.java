package com.ihr360.applet.customization.qys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihr360.applet.customization.qys.model.thirdpart.response.Check;
import com.ihr360.applet.customization.qys.model.thirdpart.response.TargetCheckListResponse;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class TimeTest {


    public static void main(String[] args) {
        // 使用给定的毫秒数创建一个Date对象
        Date date = new Date(1744648376000L);

        // 创建一个SimpleDateFormat对象来格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化日期并打印
        String formattedDate = sdf.format(date);
        System.out.println("Formatted Date: " + formattedDate);
    }

    @Test
    public void test() {
        String jsonString = "[{\n" +
                "        \"id\": 201953,\n" +
                "        \"UserID\": 1172,\n" +
                "        \"CheckTime\": \"\\/Date(1744648376000)\\/\",\n" +
                "        \"io_year\": 2025,\n" +
                "        \"io_month\": 4,\n" +
                "        \"io_day\": 15,\n" +
                "        \"io_hour\": 0,\n" +
                "        \"io_minute\": 32,\n" +
                "        \"DigitalCheckMinutes\": 29077952,\n" +
                "        \"CheckType\": 1,\n" +
                "        \"CheckDeviceID\": 46,\n" +
                "        \"Remark\": \"打卡考勤\",\n" +
                "        \"device_id\": \"C2695C93530B3330\",\n" +
                "        \"io_mode\": null\n" +
                "    }]";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 去除 JSON 中的 \/Date(...)\/ 格式
            jsonString = jsonString.replaceAll("\\\\/Date((\\d+)\\\\/)", "$1");
           TargetCheckListResponse response = new TargetCheckListResponse();
            response.setData(objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Check.class)));
            System.out.println(response);
            Check check = response.getData().get(0);
            // 原始字符串（注意Java中的转义）
            String checkTimeStr = check.getCheckTime();

            // 提取数字部分并转换为long
            String numStr = checkTimeStr.replaceAll("[^0-9]", "");
            long timestamp = Long.parseLong(numStr);

            // method 1: 转换为Date对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(new Date(timestamp));  // 格式化‌:ml-citation{ref="1,5" data="citationList"}
            System.out.println(formattedDate);  //

            //method2
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());  // 本地时区转换‌:ml-citation{ref="5,7" data="citationList"}

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate2 = dateTime.format(formatter);  // 格式化‌:ml-citation{ref="5,7" data="citationList"}
            System.out.println(formattedDate2);

            int someNumber = 123; // 示例整数
            String formattedNumber = String.format("%06d", someNumber);

            System.out.println(formattedNumber);
            check.setUserID(formattedNumber);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
