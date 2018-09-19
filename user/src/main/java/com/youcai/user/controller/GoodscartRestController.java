package com.youcai.user.controller;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import com.youcai.user.dataobject.BuyerCart;
import com.youcai.user.dataobject.BuyerItem;
import com.youcai.user.dataobject.Product;
import com.youcai.user.utils.RequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.rmic.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController

public class GoodscartRestController {

    //加入购物车
    @RequestMapping(value="/shopping/buyerCart")
    public <T> String buyerCart(Long skuId, Integer amount, HttpServletRequest request,
                                HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException{
        //将对象转换成json字符串/json字符串转成对象
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        BuyerCart buyerCart = null;
        //1,获取Cookie中的购物车
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //
                if (Constants.BUYER_CART.equals(cookie.getName())) {
                    //购物车 对象 与json字符串互转
                    buyerCart = om.readValue(cookie.getValue(), BuyerCart.class);
                    break;
                }
            }
        }

        //2,Cookie中没有购物车, 创建购物车对象
        if (null == buyerCart) {
            buyerCart = new BuyerCart();
        }

        //3, 将当前款商品追加到购物车
        if (null != skuId && null != amount) {
            Product sku = new Product();
            sku.setId(skuId);
            BuyerItem buyerItem = new BuyerItem();
            buyerItem.setSku(sku);
            //设置数量
            buyerItem.setAmount(amount);
            //添加购物项到购物车
            buyerCart.addItem(buyerItem);
        }

        //排序  倒序
        List<BuyerItem> items = buyerCart.getItems();
        Collections.sort(items, new Comparator<BuyerItem>() {

            @Override
            public int compare(BuyerItem o1, BuyerItem o2) {
                return -1;
            }

        });

        //前三点 登录和非登录做的是一样的操作, 在第四点需要判断
        String username = sessionProviderService.getAttributterForUsername(RequestUtils.getCSessionId(request, response));
        if (null != username) {
            //登录了
            //4, 将购物车追加到Redis中
            cartService.insertBuyerCartToRedis(buyerCart, username);
            //5, 清空Cookie 设置存活时间为0, 立马销毁
            Cookie cookie = new Cookie(Constants.BUYER_CART, null);
            cookie.setPath("/");
            cookie.setMaxAge(-0);
            response.addCookie(cookie);
        }else {
            //未登录
            //4, 保存购物车到Cookie中
            //将对象转换成json格式
            Writer w = new StringWriter();
            om.writeValue(w, buyerCart);
            Cookie cookie = new Cookie(Constants.BUYER_CART, w.toString());
            //设置path是可以共享cookie
            cookie.setPath("/");
            //设置Cookie过期时间: -1 表示关闭浏览器失效  0: 立即失效  >0: 单位是秒, 多少秒后失效
            cookie.setMaxAge(24*60*60);
            //5,Cookie写会浏览器
            response.addCookie(cookie);
        }

        //6, 重定向
        return "redirect:/shopping/toCart";
    }
}