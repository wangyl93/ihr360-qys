package com.ihr360.applet.customization.qys.model.thirdpart.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * AUTHOR: Sam.wang
 * DATE: 2025/3/12 17:07
 * TIME: 17:07
 */
@Data
@NoArgsConstructor
@ApiModel(value = "TargetCheckListResponse 第三方响应体")
public class TargetCheckListResponse {

    private List<Check> data; // 信息

}
