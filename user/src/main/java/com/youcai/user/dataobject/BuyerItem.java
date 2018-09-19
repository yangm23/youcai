package com.youcai.user.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;

public class BuyerItem implements Serializable {

    private static final long serialVersionUID = 1L;

    //产品对象
    private Product product;

    //购买的数量
    private BigDecimal amount;


    public Product getproduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) //比较地址
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BuyerItem other = (BuyerItem) obj;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.getId().equals(other.product.getId()))
            return false;
        return true;
    }
}
