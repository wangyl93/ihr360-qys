package com.ihr360.applet.customization.qys.model.thirdpart.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "Check 签卡记录")
public class Check {
    private long id; // 流水号
    @JsonProperty("UserID")
    private String UserID; // 员工考勤编号

    @JsonProperty("CheckTime")
    private String CheckTime; // 签卡时间

    private int io_year; // 签卡时间距离1970年1月1日的分钟数
    private int io_month;
    private int io_day;
    private int io_hour;
    private int io_minute;

    @JsonProperty("DigitalCheckMinutes")
    private long DigitalCheckMinutes; // 签卡时间距离1970年1月1日的分钟数
    @JsonProperty("CheckType")
    private int CheckType; // 签卡类型
    @JsonProperty("CheckDeviceID")
    private int CheckDeviceID; // 签卡考勤机
    @JsonProperty("Remark")
    private String Remark; // 备注信息
    private String device_id; // 签卡考勤机序列号
    private String io_mode; // IO模式
}