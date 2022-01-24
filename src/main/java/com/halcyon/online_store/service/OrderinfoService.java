package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.Orderinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface OrderinfoService extends IService<Orderinfo> {
    /**
     * 因为一个订单会有多个商品
     * 将多条记录添加到订单详情表中。
     * @param orderinfos
     */
    void addOrderInfo(List<Orderinfo> orderinfos) throws Exception;


    List<Orderinfo> getOrderInfosByOrderId(Long orderId);

    int salesProduct(Long pid);

    Orderinfo getOrderInfo(Long orderId);


}
