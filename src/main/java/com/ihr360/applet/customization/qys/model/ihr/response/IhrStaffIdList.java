package com.ihr360.applet.customization.qys.model.ihr.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IhrStaffIdList {

    public int code; // 响应状态码
    public String message; // 响应消息
    public StaffData data; // 响应数据
    public Boolean errorResult; // 错误结果标志

    @Data
    public static class StaffData {
        List<String> dataList;
        PageInfo pageInfo;
    }

    @Data
    public static class PageInfo {
        int totalCount;
        int totalPages;
        int pageNo;
        int pageSize;
    }
}
