package com.youcai.user.exceptions;



import com.youcai.user.enums.ResultEnum;
import lombok.Getter;

@Getter
public class HintException extends RuntimeException {

    public HintException(String msg){
        super(msg);
    }
}

