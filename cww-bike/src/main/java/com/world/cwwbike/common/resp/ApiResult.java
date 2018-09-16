package com.world.cwwbike.common.resp;

import com.world.cwwbike.common.constants.Constants;
import lombok.Data;

@Data
public class ApiResult<T> {

    private int code = Constants.RESP_STATUS_OK;

    private String message;

    private T data;
}
