package com.ihr360.applet.customization.qys.model.ihr.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IhrDeptBasic {

    @ApiModelProperty("数据编号")
    @JsonProperty(value = "uuid")
    private String id;

    @ApiModelProperty("部门编号")
    @JsonProperty(value = "id")
    private Long departmentId;
    @ApiModelProperty("名称")
    @JsonProperty(value = "name")
    private String departmentName;
    @ApiModelProperty("部门编码")
    private String departmentCode;

    @ApiModelProperty("父部门id")
    @JsonProperty(value = "parentId")
    private Long parentDepartmentId;
    @ApiModelProperty("父部门名称")
    private String parentDepartmentName;
    @ApiModelProperty("父部门code")
    private String parentDepartmentCode;

    @ApiModelProperty("部门状态")
    private String departmentStatus;

    @ApiModelProperty("部门负责人id")
    private String principalStaffId;

    @ApiModelProperty("部门路径")
    private String departmentPath;
}
