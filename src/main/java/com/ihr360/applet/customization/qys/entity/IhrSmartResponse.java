package com.ihr360.applet.customization.qys.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * AUTHOR: 艾力（XI RE LI · MA MAT）
 * DATE: 2023/9/3
 * TIME: 17:07
 */
@Data
@NoArgsConstructor
@ApiModel(value="IHRApiResponse IHR响应体")
public class IhrSmartResponse {

    private int code; // 响应状态码
    private String message; // 响应消息
    private Object data; // 响应数据
    private Boolean errorResult; // 错误结果标志


}
