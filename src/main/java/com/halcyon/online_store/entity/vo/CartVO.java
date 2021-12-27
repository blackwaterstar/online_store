package com.halcyon.online_store.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartVO {

    /*
     t1.cart_id,t1.user_id,t1.pid,t2.pname,t2.price,t1.pcount
     */

    private Long cartId;
    private Long userId;
    private Long ppid;
    private String pname;
    private BigDecimal price;
    private Long pcount;


}
