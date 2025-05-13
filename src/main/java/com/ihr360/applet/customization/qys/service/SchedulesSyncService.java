package com.ihr360.applet.customization.qys.service;

public interface SchedulesSyncService {
    Object syncSchedules(String specifiedDay);

    Object checkStaffDiff();
}
