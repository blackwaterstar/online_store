package com.halcyon.online_store.entity.vo;

import com.halcyon.online_store.entity.ProductInfo;
import com.halcyon.online_store.entity.tOrder;
import lombok.Data;

import java.util.List;

/**
 * 跟订单相关的数据实体
 */
@Data
public class OrderVO {

    private com.halcyon.online_store.entity.tOrder tOrder;

    //当前订单所有商品的集合
    private List<ProductInfo> productInfos;

    private List<Long> pcounts;

}
