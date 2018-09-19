package com.youcai.user.dataobject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * 联合主键：司机id、客户id、送货日期、产品id
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverListKey implements Serializable {

    @Column(name = "oid")
    private Integer oid;

    private String guestId;

    private Date ddate;

    private String productId;

    private String state;

    private Date orderDate;
}

