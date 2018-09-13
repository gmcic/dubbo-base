package org.dubbo.x.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO to customize the returned message
 *
 * @author lishen
 */
@Getter
@Setter
public class RestResult<T> implements Serializable {
    public static final int OK = 0;
    public static final String M_OK = "成功";

    public static <T> RestResult OK(T data) {
        RestResult restResult = new RestResult();
        restResult.setErrorMessage(M_OK);
        restResult.setErrorCode(OK);
        restResult.setData(data);
        return restResult;
    }
    

    private int errorCode;
    private String errorMessage;
    private T data;

    public static <T> RestResult REST_RESULT(RestResultEnum unauthorized, T message) {
        RestResult restResult = new RestResult();
        restResult.setErrorMessage(unauthorized.getDesc());
        restResult.setErrorCode(unauthorized.getCode());
        restResult.setData(message);
        return restResult;
    }
}
