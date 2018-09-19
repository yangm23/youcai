package com.youcai.user.vo.product;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class OneV0 {
    private String id;

    private String name;

    /*--- 产品大类编码 ---*/
    private String pCode;

    /*--- 产品单位 ---*/
    private String unit;

    private BigDecimal price;

    /*--- 产品图片文件名 ---*/
    private String imgfile;

    private String note;

    /*--- 产品大类名称 ---*/
    private String pCodeName;
}
