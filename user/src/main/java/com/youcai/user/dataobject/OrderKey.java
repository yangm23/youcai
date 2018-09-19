package com.youcai.user.dataobject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * 客户采购单联合主键
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderKey implements Serializable {

    @Column(name = "oid")
    private Integer oid;
    /*--- 订单时间 ---*/
    private Date odate;

    /*--- 订单用户id ---*/
    private String guestId;

    /*--- 订单产品id ---*/
    private String productId;

    /*-- 订单取货站点id --*/
    private String stationId;

    /*--- 采购单状态, "0"正常, 其它退回 ---*/
    private String state;
}
