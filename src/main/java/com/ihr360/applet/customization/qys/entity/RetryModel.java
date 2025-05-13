package com.ihr360.applet.customization.qys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AUTHOR: 艾力（XI RE LI · MA MAT）
 * DATE: 2024/05/17
 * TIME: 23:24
 */
@Data
@AllArgsConstructor
public class RetryModel<T> {

    private Boolean status;

    private T responseData;

    public RetryModel(Boolean status) {
        this.status = status;
    }
}
