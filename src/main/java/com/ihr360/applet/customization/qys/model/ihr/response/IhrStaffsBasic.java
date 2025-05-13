package com.ihr360.applet.customization.qys.model.ihr.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IhrStaffsBasic extends StaffDetailInfoWithSuperior {

    @ApiModelProperty("员工id")
    @JsonProperty(value = "staffId")
    private String id;

    @ApiModelProperty("员工姓名")
    private String staffName;

    @ApiModelProperty("职级名称")
    private String  positionLevelName;

    @ApiModelProperty("职务名称")
    private String jobTitleName;

    @ApiModelProperty("职位名称")
    private String positionName;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("部门id")
    private Long departmentId;

    @ApiModelProperty("手机号")
    private String mobileNo;

    @ApiModelProperty("直属领导")
    private String superiorId;

    @ApiModelProperty("工作电话")
    private String workPhone;

    @ApiModelProperty("公司网站名称")
    private String companySiteName;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("生日")
    private String birthday;

    @ApiModelProperty("注册日期")
    private String enrollInDate;

    @ApiModelProperty("邮件")
    private String workEmail;

    @ApiModelProperty("人员备注")
    private String staffRemark;

    @ApiModelProperty("兼任")
    private String assignedPositions;

    @ApiModelProperty("工号")
    private String staffNo;

    @ApiModelProperty("证件类型")
    private String idCardType;

    @ApiModelProperty("证件号")
    private String idCardNo;

    @ApiModelProperty("离职日期")
    private String leaveDate;

    @ApiModelProperty("所属部门编号")
    private String departmentCode;

    @ApiModelProperty("人员路径")
    private String staffPath;

    @ApiModelProperty("flex属性")
    private Map<String,Object> flexAttributes;

    @ApiModelProperty("是否主部门")
    private Boolean is_main_administration_unit;

    @ApiModelProperty("头像Id")
    private String staffImageId;

    @ApiModelProperty("创建日期")
    private String createdDate;

    @ApiModelProperty("最后修改日期")
    private String lastUpdateDate;

    @Data
    public static class SuperiorsInfo {
        String staffId;
        String superiorId;
        String reportToType;
    }
}
