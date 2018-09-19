package com.youcai.user.service;


import com.youcai.user.dataobject.Guest;

public interface GuestService {
    Guest update(Guest guest);
    void updatePwd(String pwd);
    Guest findOne(String id);
    Guest save(Guest guest);
    Guest findCurrent();
    boolean isPhoneRepeat(String phone);
    boolean isPhoneRepeat(String phone, String guestId);
}
