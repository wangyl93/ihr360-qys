package com.ihr360.applet.customization.qys.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Intellij IDEA.
 * Author: Sam.wang
 * Email:  2823741075@qq.com
 * Date:  2021/5/27 13:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EventHandleException extends RuntimeException {

    private String msg;

    public EventHandleException(String msg) {
        super("custom not exist");
        this.msg = msg;
    }
}
