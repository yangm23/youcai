package com.youcai.user.exceptions;

import com.youcai.user.enums.ResultEnum;
import lombok.Getter;

@Getter
public class GuestException extends RuntimeException {

    private Integer code;

    public GuestException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public GuestException(String msg){
        super(msg);
        this.code = ResultEnum.ERROR.getCode();
    }
}
