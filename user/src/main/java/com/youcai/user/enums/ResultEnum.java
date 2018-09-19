package com.youcai.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultEnum {

    NO_LOGIN(1, "未登录"),
    SUCCESS(0, "成功"),
    ERROR(2, "错误")
    ;
    private Integer code;
    private String note;


}
