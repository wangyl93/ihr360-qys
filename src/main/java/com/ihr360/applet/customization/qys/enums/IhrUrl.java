package com.ihr360.applet.customization.qys.enums;

import lombok.Getter;

@Getter
public enum IhrUrl {

    ACCESSTOKEN( "{}/openapi/oauth/token?grant_type=client_credentials&scope=client", "认证授权"),
    RFRRESHTOKEN("{}/openapi/oauth/token?grant_type=refresh_token&refresh_token={}", "重新获取认证凭证"),
    ORGANIZATION("/org/v2/companies/organizations", "获取部门清单v2"),

    CORPORATIONPOSITION("/org/v1/organizations/positions", "获取公司职位清单"),
    CORPORATIONJOBTITLE("/org/v1/organizations/jobTitles", "获取职务清单"),
    GRADESYSTEM("/org/v1/gradesystem/all", "获取职级体系表清单"),

    //员工
    STAFFID("/staff/v1/staffs/ids", "获取员工ID清单"),
    STAFFBASIC("/staff/v1/staffs/basic", "批量获取员工基本信息"),
    STAFFADDITION("/staff/v1/staffs/additions", "获取当日新增员工ID清单"),
    STAFFMODIFICATION("/staff/v1/staffs/modifications", "获取当日更新的员工ID清单"),
    STAFFDELETED("/staff/v1/staffs/deleted", "获取删除员工清单"),
    STAFFIDBYSTAFFNO("/staff/v1/staffs/getStaffIdByStaffNo", "根据员工工号获取员工ID"),
    GETIDBYSTAFFNO("/staff/v1/staffs/getStaffIdByStaffNo", "根据员工工号获取员工ID"),
    CERTIFICATES("/staff/v1/certificates", "获取员工证照清单"),
    GETFILESURL("/file/v1/batch/get/files/info", "批量获取文件url和文件名"),
    COMPLETEDRECORD("/v1/staff/completed/record/list", "获取员工已签署的签署记录"),
    SUBSET_METADATA("/staff/v1/subset/metadata", "获取员工信息子集元数据定义"),
    SUBSET("/staff/v1/subset", "获取员工自定义信息子集记录"),
    ENTRYFORMS("/staff/v1/entryForms", "获取待入职清单"),
    STAFFIDBASIC("/staff/v1/staffs/{staffId}/basic", "获取员工信息详情"),
    STAFFDETAIL("/staff/v1/staffs/{staffId}/detail", "获取员工信息详情"),
    STAFFDETAILV2("/staff/v1/staffs/{staffId}/superiors/detail", "获取员工信息详情V2"),
    STAFFMODIFICATIONS("/staff/v2/staffs/modifications", "获取当日更新的员工ID清单V2"),
    STAFFADDITIONS("/staff/v1/staffs/additions", "获取当日新增员工ID清单"),
    STAFFDELETEDS("/staff/v1/staffs/deleted", "获取删除员工清单"),
    STAFFSUBSET("/staff/v1/subset", "获取员工自定义信息子集记录"),
    EDUCATION("/staff/v1/educations", "获取员工自定义信息子集记录"),
    STAFFIMAGEBASE64("/tm/v1/face/images/search", "批量查询人脸base64数据"),

    CORPORATION("/org/v1/corporations", "获取法律实体清单v2"),
    COSTCENTER("/org/v1/costcenter/get/list", "获取成本中心列表"),

    V2_SCHEDULES("/tm/v2/schedules/actual/search", "获取排班V2"),
    SHIFTS_LIST("/tm/v1/schedules/shifts/blocks/list/search", "获取班次和班段"),

    SMARTAPP_LIST("/smartapp/v2/data/list","智搭云表单数据分页查询"),
    SMARTAPP_DELETE("/smartapp/v1/data/delete","智搭云表单数据删除"),
    UPSERT("/smartapp/v1/data/upsert","向智搭云中插入数据"),
    SIGNS_SEARCH("/tm/v1/signs/records/search","获取打卡记录"),
    SIGNS_CREATE("/tm/v2/signs/records/create","新增打卡记录V2"),
    SALARYRESULT("/payroll/v1/salaryResult/list", "根据薪资项目获取员工月度薪资结果");




    private String url;
    /**
     * name字段对应接口名称
     */
    private String name;

    IhrUrl(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }
}
