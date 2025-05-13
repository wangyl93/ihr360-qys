package com.ihr360.applet.customization.qys.model.thirdpart.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "KqUser 用户信息")
public class KqUser {
    @JsonProperty("UserID")
    private Integer userID; // 用户ID

    @JsonProperty("GlobalID")
    private Integer globalID; // 全局ID

    @JsonProperty("EmployeeNo")
    private String employeeNo; // 员工编号

    @JsonProperty("Mobile")
    private String mobile; // 手机号

    @JsonProperty("IsMgr")
    private Boolean isMgr; // 是否是经理

    @JsonProperty("DeptID")
    private Integer deptID; // 部门ID

    @JsonProperty("RealName")
    private String realName; // 真实姓名

    @JsonProperty("IdentityType")
    private Integer identityType; // 身份类型

    @JsonProperty("IdentityNo")
    private String identityNo; // 身份号码

    @JsonProperty("HeadImg")
    private String headImg; // 头像

    @JsonProperty("Sex")
    private Integer sex; // 性别

    @JsonProperty("Degree")
    private Integer degree; // 学历

    @JsonProperty("Email")
    private String email; // 邮箱

    @JsonProperty("Phone")
    private String phone; // 电话

    @JsonProperty("Address")
    private String address; // 地址

    @JsonProperty("LastLoginIP")
    private String lastLoginIP; // 最后登录IP

    @JsonProperty("LastLoginTime")
    private Date lastLoginTime; // 最后登录时间

    @JsonProperty("Status")
    private Integer status; // 状态

    @JsonProperty("IsHighManager")
    private Boolean isHighManager; // 是否是高管

    @JsonProperty("NeedKaoQin")
    private Boolean needKaoQin; // 是否需要考勤

    @JsonProperty("AppHasPay")
    private Boolean appHasPay; // 应用是否有支付

    @JsonProperty("WorkType")
    private Integer workType; // 工作类型

    @JsonProperty("WorkLevel")
    private Integer workLevel; // 工作级别

    @JsonProperty("BirthDate")
    private Date birthDate; // 出生日期

    @JsonProperty("EnterDate")
    private Date enterDate; // 入职日期

    @JsonProperty("LeaveDate")
    private Date leaveDate; // 离职日期

    @JsonProperty("LeaveType")
    private String leaveType; // 离职类型

    @JsonProperty("LeaveReson")
    private String leaveReson; // 离职原因

    @JsonProperty("Remark")
    private String remark; // 备注

    @JsonProperty("FKPwd")
    private String fKPwd; // 密码

    @JsonProperty("FKCardNo")
    private String fKCardNo; // 卡号

    @JsonProperty("DeptName")
    private String deptName; // 部门名称

    @JsonProperty("FKFinger0Len")
    private Integer fKFinger0Len; // 指纹0长度

    @JsonProperty("FKFinger1Len")
    private Integer fKFinger1Len; // 指纹1长度

    @JsonProperty("FKFinger2Len")
    private Integer fKFinger2Len; // 指纹2长度

    @JsonProperty("FKFinger3Len")
    private Integer fKFinger3Len; // 指纹3长度

    @JsonProperty("FKFinger4Len")
    private Integer fKFinger4Len; // 指纹4长度

    @JsonProperty("FKFinger5Len")
    private Integer fKFinger5Len; // 指纹5长度

    @JsonProperty("FKFinger6Len")
    private Integer fKFinger6Len; // 指纹6长度

    @JsonProperty("FKFinger7Len")
    private Integer fKFinger7Len; // 指纹7长度

    @JsonProperty("FKFinger8Len")
    private Integer fKFinger8Len; // 指纹8长度

    @JsonProperty("FKFinger9Len")
    private Integer fKFinger9Len; // 指纹9长度

    @JsonProperty("FKFaceLen")
    private Integer fKFaceLen; // 人脸长度

    @JsonProperty("FK_Palm1Len")
    private Integer fK_Palm1Len; // 掌纹1长度

    @JsonProperty("FK_Palm2Len")
    private Integer fK_Palm2Len; // 掌纹2长度

    @JsonProperty("NkqPhotoLen")
    private Integer nkqPhotoLen; // 签卡照片长度

    @JsonProperty("NkqFaceLen")
    private Integer nkqFaceLen; // 签卡人脸长度

    @JsonProperty("IdentityTypeName")
    private String identityTypeName; // 身份类型名称

    @JsonProperty("DegreeName")
    private String degreeName; // 学历名称

    @JsonProperty("WorkTypeName")
    private String workTypeName; // 工作类型名称

    @JsonProperty("WorkLevelName")
    private String workLevelName; // 工作级别名称
}

