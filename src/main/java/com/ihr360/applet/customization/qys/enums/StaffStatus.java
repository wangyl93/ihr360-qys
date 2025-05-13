package com.ihr360.applet.customization.qys.enums;

import lombok.Getter;

@Getter
public enum StaffStatus {

    IN_SERVICE("在职", 1), 
    QUIT("离职", 2), 
    DELETE("删除", 3);

    private final String desc;
    /**
     * code字段对应三方系统中的人员对象的state字段值
     */
    private final Integer code;

    StaffStatus(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }


}
