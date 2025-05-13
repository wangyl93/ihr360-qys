package com.ihr360.applet.customization.qys.controller;

import com.ihr360.applet.customization.qys.api.KqServiceApi;
import com.ihr360.applet.customization.qys.service.SchedulesSyncService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sync")
public class SchedulesSyncController {

    @Resource
    private SchedulesSyncService schedulesSyncService;

    @Resource
    KqServiceApi kqServiceApi;



    @RequestMapping(path = "/getDeviceList", method = RequestMethod.GET)
    public Object getDeviceList() {
        return kqServiceApi.getAllDeviceList();
    }

    @RequestMapping(path = "/getUserList", method = RequestMethod.GET)
    public Object getUserList() {
        return kqServiceApi.getUserList();
    }

    @RequestMapping(path = "/syncSchedules", method = RequestMethod.GET)
    public Object syncSchedules(@RequestParam(name = "specifiedDay", defaultValue = "", required = false) String specifiedDay) {
        return schedulesSyncService.syncSchedules(specifiedDay);
    }

    @RequestMapping(path = "/checkStaffDiff", method = RequestMethod.GET)
    public Object checkStaffDiff() {
        return schedulesSyncService.checkStaffDiff();
    }


}
