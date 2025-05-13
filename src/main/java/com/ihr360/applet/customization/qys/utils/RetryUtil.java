package com.ihr360.applet.customization.qys.utils;

import com.ihr360.applet.customization.qys.entity.RetryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class RetryUtil {
    private static final Logger logger = LoggerFactory.getLogger(RetryUtil.class);

    public static RetryModel<?> retry(Integer maxRetries, Integer waitingTime, Callable<RetryModel<?>> callable){
        int retryCount = 0;
        RetryModel<?> call = new RetryModel<>(false);
        while (retryCount < maxRetries) {
            try {
                call = callable.call(); // 调用目标方法
                if(call.getStatus()){
                    break;
                }else{
                    logger.error("重试");
                    TimeUnit.SECONDS.sleep(waitingTime);
                }
            } catch (Exception e) {
                try {
                    TimeUnit.SECONDS.sleep(waitingTime);
                } catch (InterruptedException ex) {
                    logger.error("重试异常",ex);
                    throw new RuntimeException(ex);
                }
                logger.error("重试异常",e);
            }
            retryCount++;
        }
        return call;
    }
}
