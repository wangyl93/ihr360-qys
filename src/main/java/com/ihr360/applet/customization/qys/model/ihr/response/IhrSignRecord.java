package com.ihr360.applet.customization.qys.model.ihr.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "Check 签卡记录")
public class IhrSignRecord {
    private String staffId;//员工标识
    private String signTime;//打卡时间

    private String signAddress;
    private String sourceDescription;
    private String signType;

}
