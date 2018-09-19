package com.youcai.user.handler;

import com.youcai.user.exceptions.GuestException;
import com.youcai.user.exceptions.HintException;
import com.youcai.user.utils.ResultVOUtils;
import com.youcai.user.vo.ResultVO;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GuestExceptionHandler {

    @ExceptionHandler(GuestException.class)
    @ResponseBody
    public ResultVO handleGuestException(GuestException e){
        return ResultVOUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultVO handleGuestException(BindException e){
        return ResultVOUtils.error(e.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(HintException.class)
    @ResponseBody
    public ResultVO handleGuestException(HintException e){
        return ResultVOUtils.success(e.getMessage());
    }

}
