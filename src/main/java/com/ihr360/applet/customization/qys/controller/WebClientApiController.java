package com.ihr360.applet.customization.qys.controller;

import com.ihr360.applet.customization.qys.api.WebClientApi;
import com.ihr360.applet.customization.qys.service.StaffBasicSyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class WebClientApiController {

    @Resource
    WebClientApi webClientApi;

    @Resource
    StaffBasicSyncService staffBasicSyncService;

    @GetMapping("/getToken")
    public Object getToken() throws Exception {
        return webClientApi.getToken();
    }
    @GetMapping("/getRefreshToken")
    public Object getRefreshToken() throws Exception {
        return webClientApi.getRefreshToken();
    }

    @GetMapping("/syncStaffBasic")
    public Object syncStaffBasic() throws Exception {
        return staffBasicSyncService.syncStaffBasic();
    }


}
