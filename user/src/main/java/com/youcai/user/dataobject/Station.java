package com.youcai.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Station {
    @Id
    private Integer id;

    private String name;

    private String Location;

    private String LocationIp;

    private String sPhone;

    private String note;

    private String contact1;

    private String phone1;

    private String contact2;

    private String phone2;

    private String contact3;

    private String phone3;


}
