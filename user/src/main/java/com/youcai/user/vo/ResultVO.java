package com.youcai.user.vo;


import lombok.Data;

@Data
public class ResultVO<T> {

    /*--- 状态码 ---*/
    private Integer code;

    private Integer count;

    /*--- 描述信息 ---*/
    private String msg;

    /*--- 返回的数据 ---*/
    private T data;

    public ResultVO() { }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(Integer code, Integer count, String msg, T data) {
        this.code = code;
        this.count = count;
        this.msg = msg;
        this.data = data;
    }
}
