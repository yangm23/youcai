package com.youcai.user.form.guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateForm {

    @NotEmpty(message = "名称不能为空")
    private String name;

    private String addr;

    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$", message = "手机号不存在")
    private String phone;

    private String mobile1;

    private String leader1;

    private String mobile2;

    private String leader2;

    private String note;
}
