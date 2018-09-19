package com.youcai.user.dataobject;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Goodscart {
    @Id
    private Integer id;

    private Date addtime;

    private  Integer deletestatus;

    private BigDecimal count;

    private BigDecimal price;

    private  String spec_info;

}
