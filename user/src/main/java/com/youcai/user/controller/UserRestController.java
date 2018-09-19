package com.youcai.user.controller;



import com.youcai.user.dataobject.Guest;
import com.youcai.user.enums.ResultEnum;
import com.youcai.user.exceptions.GuestException;
import com.youcai.user.form.guest.RegisterForm;
import com.youcai.user.form.guest.UpdateForm;
import com.youcai.user.form.guest.UpdatePwdForm;
import com.youcai.user.service.GuestService;
import com.youcai.user.utils.EDSUtils;
import com.youcai.user.utils.ResultVOUtils;
import com.youcai.user.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("update")
    public ResultVO<Guest> update(
            @Valid UpdateForm form
    ){
        Guest guest = new Guest();
        BeanUtils.copyProperties(form, guest);

        return ResultVOUtils.success(guestService.update(guest));
    }

    @PostMapping("updatePwd")
    public ResultVO<Guest> updatePwd(
            @Valid UpdatePwdForm updatePwdForm
    ){
        if (!updatePwdForm.getNewPwd().equals(updatePwdForm.getReNewPwd())){
            return ResultVOUtils.error("两次密码输入不一致");
        }
        if (!EDSUtils.encryptBasedDes(updatePwdForm.getOldPwd()).equals(guestService.findCurrent().getPwd())){
            return ResultVOUtils.error("原密码错误");
        }

        guestService.updatePwd(updatePwdForm.getNewPwd());

        return ResultVOUtils.success("更新密码成功");
    }

    @PostMapping("/register")
    public ResultVO<Guest> register(
            @Valid RegisterForm form,
            @RequestParam String repwd
    ){
        Guest guest = new Guest();
        BeanUtils.copyProperties(form, guest);

        if (!guest.getPwd().equals(repwd)){
            throw new GuestException("两次密码输入不一致");
        }

        Guest result = guestService.save(guest);

        return ResultVOUtils.success(result);
    }
}
