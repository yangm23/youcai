package com.youcai.user.dataobject;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcai.user.utils.EDSUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

@Entity
@Data
public class Guest implements UserDetails {

    @Id
    private String id;

    @JsonIgnore
    /*--- 客户密码 ---*/
    private String pwd;

    private String name;

    private String addr;

    private String phone;


    private String note;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return EDSUtils.decryptBasedDes(this.pwd);
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.phone;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
