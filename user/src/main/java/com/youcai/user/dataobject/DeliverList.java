package com.youcai.user.dataobject;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "d_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverList {

    /*---  * 联合主键：司机id、客户id、送货日期、产品id ---*/
    @EmbeddedId
    private DeliverListKey id;

    private BigDecimal price;

    private BigDecimal num;

    private BigDecimal amount;

    private String note;
}
