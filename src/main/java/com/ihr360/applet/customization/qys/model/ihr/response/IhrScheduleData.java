package com.ihr360.applet.customization.qys.model.ihr.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IhrScheduleData {
    @ApiModelProperty(value="员工ID")
    private String staffId;

    @ApiModelProperty(value="班次ID")
    private String shiftId;

    @ApiModelProperty(value="班次缩写")
    private String shiftAbbr;

    @ApiModelProperty(value="计划日期")
    private String planDate;

    @ApiModelProperty(value="是否是工作日")
    private boolean isWorkingDay;
}
