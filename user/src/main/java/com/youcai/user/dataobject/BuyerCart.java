package com.youcai.user.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

 public class BuyerCart implements Serializable {

    /**
     * 购物车
     */
    private static final long serialVersionUID = 1L;

    //商品结果集
    private List<BuyerItem> items = new ArrayList<BuyerItem>();

    //添加购物项到购物车
    public void addItem(BuyerItem item){
        //判断是否包含同款
        if (items.contains(item)) {
            //追加数量
            for (BuyerItem buyerItem : items) {
                if (buyerItem.equals(item)) {
                    buyerItem.setAmount(item.getAmount().add(buyerItem.getAmount()));
                }
            }
        }else {
            items.add(item);
        }

    }

    public List<BuyerItem> getItems() {
        return items;
    }

    public void setItems(List<BuyerItem> items) {
        this.items = items;
    }


    //小计
    //商品数量
    @JsonIgnore
    public BigDecimal getProductAmount(){
        BigDecimal result=BigDecimal.ZERO;
        //计算
        for (BuyerItem buyerItem : items) {
            result = buyerItem.getAmount().add(result);
        }
        return result;
    }

    //商品金额
    @JsonIgnore
    public BigDecimal getProductPrice(){
        BigDecimal result=BigDecimal.ZERO;
        //计算
        for (BuyerItem buyerItem : items) {
            result= buyerItem.getAmount().multiply(buyerItem.getproduct().getPrice()).add(result);
        }
        return result;
    }


    //总价
    @JsonIgnore
    public BigDecimal getTotalPrice(){
        return getProductPrice();
    }

}
