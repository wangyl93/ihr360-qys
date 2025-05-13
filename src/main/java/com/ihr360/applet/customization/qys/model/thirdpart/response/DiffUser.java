package com.ihr360.applet.customization.qys.model.thirdpart.response;

import lombok.Data;

@Data
public class DiffUser {
    private String userName;
    private String ihrEmployeeNo;
    private String kqEmployeeNo;
    private String description;

    public DiffUser(String userName, String ihrEmployeeNo, String kqEmployeeNo, String description) {
        this.userName = userName;
        this.ihrEmployeeNo = ihrEmployeeNo;
        this.kqEmployeeNo = kqEmployeeNo;
        this.description = description;
    }
}
