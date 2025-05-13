package com.ihr360.applet.customization.qys.model.ihr.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class IhrSchedulesPageData {
    @ApiModelProperty(value="总页数")
    private int totalPages;

    @ApiModelProperty(value="总元素数")
    private int totalElements;

    @ApiModelProperty(value="内容列表")
    private List<IhrScheduleData> content;

}
