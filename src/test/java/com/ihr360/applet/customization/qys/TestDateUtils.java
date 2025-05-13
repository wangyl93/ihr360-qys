package com.ihr360.applet.customization.qys;

import com.ihr360.applet.customization.qys.utils.DateUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestDateUtils {
    @Test
    public void test() {
        String minDate = DateUtils.getMinDate(6);
        System.out.println("minDate = " + minDate);
        minDate = minDate.substring(0,7) + "-03 00:00:00";
        System.out.println("new minDate = " + minDate);


        //获取startDate00:00:00的时间戳
        LocalDateTime startOfDay = LocalDate.parse("2025-01-01").atStartOfDay();
        ZonedDateTime startOfDayZoned = startOfDay.atZone(ZoneId.systemDefault());
        long startOfDayTimestamp = startOfDayZoned.toInstant().toEpochMilli();
        //获取endDate23:59:59的时间戳
        LocalDateTime endOfDay = LocalDate.parse("2025-01-01").atTime(23, 59, 59);
        ZonedDateTime endOfDayZoned = endOfDay.atZone(ZoneId.systemDefault());
        long endOfDayTimestamp = endOfDayZoned.toInstant().toEpochMilli();

        System.out.println("startOfDayTimestamp = " + startOfDayTimestamp);
        System.out.println("endOfDayTimestamp = " + endOfDayTimestamp);

    }
}
