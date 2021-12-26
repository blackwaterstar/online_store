package com.halcyon.online_store.entity.vo;

import com.halcyon.online_store.entity.Order;
import com.halcyon.online_store.entity.Product;
import com.halcyon.online_store.entity.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * 跟订单相关的数据实体
 */
@Data
public class OrderVO {

    private Order tOrder;

    //当前订单所有商品的集合
    private List<ProductInfo> productInfos;

    private List<Long> pcounts;

}
