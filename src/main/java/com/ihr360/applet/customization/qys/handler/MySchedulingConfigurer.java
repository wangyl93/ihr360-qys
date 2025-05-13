package com.ihr360.applet.customization.qys.handler;

import com.ihr360.applet.customization.qys.service.SchedulesSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
@Component
public class MySchedulingConfigurer implements SchedulingConfigurer {

    @Value("${Scheduling.job.enable}")
    private boolean enable;

    @Value("${Scheduling.job.cron}")
    private String cron;

    private static final Logger logger = LoggerFactory.getLogger(MySchedulingConfigurer.class);

    @Resource
    SchedulesSyncService schedulesSyncService;




    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());

        if (enable) {
            taskRegistrar.addTriggerTask(
                    // 第一个定时任务
                    () -> {
                        logger.info("执行定时,同步打卡数据: " + System.currentTimeMillis());
                        schedulesSyncService.syncSchedules("");
                    },
                    triggerContext -> {
                        // 使用Cron表达式设置执行频率，在周一到周五的每天早上7点到晚上8点之间每小时执行一次
                        return new CronTrigger(cron).nextExecutionTime(triggerContext);
                    }
            );
        }
    }

    // 使用多线程
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(1);
    }

}
