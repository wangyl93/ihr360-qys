package com.ihr360.applet.customization.qys.model.thirdpart.response;

import lombok.Data;

import java.util.List;

@Data
public class DiffUserList {
    private List<DiffUser> sameUserList;
    private List<DiffUser> diffUserList;
    private List<DiffUser> ihrUserList;
    private List<DiffUser> kqUserList;

    public DiffUserList() {
    }

    public DiffUserList(List<DiffUser> sameUserList, List<DiffUser> diffUserList, List<DiffUser> ihrUserList, List<DiffUser> kqUserList) {
        this.sameUserList = sameUserList;
        this.diffUserList = diffUserList;
        this.ihrUserList = ihrUserList;
        this.kqUserList = kqUserList;
    }
}
