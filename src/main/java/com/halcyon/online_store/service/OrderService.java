package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.dto.*;
import com.halcyon.online_store.entity.tOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.halcyon.online_store.entity.vo.OrderVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface OrderService extends IService<tOrder> {
    void addOrder(OrderVO orderVO);

    ResultDTO addOrder(OrderDTO orderDTO);

    List<CreateOrderDTO> getList(Long userId);
//    List<CreateOrderDTO> getList1(Long userId);
List<CreateOrderDTO> getList1(Long userId);

    List<CreateOrderDTO> getList2(Long userId);

    tOrder getorder(Long orderId);

    Integer updatestatme(Long orderId);

    Integer updateOrder(tOrder order);

    List<CreateOrderDTO> getList3();

    List<CreateOrderDTO> getList4(Long userId);

    Integer deleteOrder(Long orderId);

    saleDTO SaleData();

    saleInfoDTO saleDataInfo();


    // Integer apiUpdateOrder(tOrder tOrder);
}
