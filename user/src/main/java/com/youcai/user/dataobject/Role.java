package com.youcai.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
/*用户角色类型*/
public class Role {
    @Id
    private String id;

    private  String  name;
    /* 购买权限*/
    private  Integer  bperm;
   /*管理权限 */
    private  Integer  mperm;





}
