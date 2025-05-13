package com.ihr360.applet.customization.qys.model.ihr.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IhrScheduleSmartData {
    @ApiModelProperty(value="员工ID")
    private String staffId;

    @ApiModelProperty(value="员工姓名")
    private String staffName;

    @ApiModelProperty(value="员工工号")
    private String staffNo;

    @ApiModelProperty(value="班次ID")
    private String shiftId;

    @ApiModelProperty(value="早餐次数")
    private Integer breakTimes;

    @ApiModelProperty(value="午餐次数")
    private Integer lunchTimes;

    @ApiModelProperty(value="晚餐次数")
    private Integer dinnerTimes;

    @ApiModelProperty(value="夜班次数")
    private Integer nightTimes;


}
