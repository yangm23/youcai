package com.youcai.user.utils;



import com.youcai.user.dataobject.Guest;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static Guest getCurrentUser(){
        return (Guest) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
