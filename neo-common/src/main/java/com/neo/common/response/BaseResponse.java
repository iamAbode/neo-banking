package com.neo.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author ABODE
 * @Date 2025/03/08 8:13 AM
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse <T> {
    private String responseCode;
    private boolean status;
    private T data;

    public BaseResponse(String responseCode, boolean status, T data) {
        this.responseCode = responseCode;
        this.status = status;
        this.data = data;
    }
}
