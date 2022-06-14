package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.dto.*;
import com.halcyon.online_store.entity.tOrder;
import com.halcyon.online_store.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@RestController
@RequestMapping("//order")
public class OrderController {

    @Resource
    private OrderService orderService;


    @RequestMapping("addOrder")
    public ResultDTO addOrder(OrderDTO orderDTO){
        return  orderService.addOrder(orderDTO);

    }

    /**
     * 获取当前用户的所有订单
     * @param userId
     * @return
     */
    @RequestMapping("list")
    public List<CreateOrderDTO> getList(Long userId){
        return orderService.getList(userId);
    }

    @RequestMapping("saleData")
    public saleDTO saleData(){
        return orderService.SaleData();
    }

    @RequestMapping("saleDataInfo")
    public saleInfoDTO saleDataInfo(){
        return orderService.saleDataInfo();
    }


    /**
     * 获取用户已付款订单
     * @return
     */
    @RequestMapping("list1")
    public List<CreateOrderDTO> getList1(Long userId){
        return orderService.getList1(userId);
    }

    @RequestMapping("list4")
    public List<CreateOrderDTO> getList4(Long userId){
        return orderService.getList4(userId);
    }

    /**
     * 获取用户未付款订单
     * @return
     */
    @RequestMapping("list2")
    public List<CreateOrderDTO> getList2(Long userId){
        return orderService.getList2(userId);
    }

    /**
     * 获取所有订单
     * @return
     */
    @RequestMapping("list3")
    public List<CreateOrderDTO> getList(){
        return orderService.getList3();
    }


//    @RequestMapping("list1")
//    public List<CreateOrderDTO> getList1(Long userId){
//        return orderService.getList1(userId);
//    }


//    //根据用户账户和订单id查找订单
//    @RequestMapping("list2")
//    public List<CreateOrderDTO> getList2(Long orderId,Long userId){
//        return orderService.getList2(orderId,userId);
//    }



    @RequestMapping("getOrder")
    public tOrder getOrder(Long orderId){
        return orderService.getorder(orderId);
    }



    @RequestMapping("updatestatme")
    public Integer updatestatme(Long orderId){
        return orderService.updatestatme(orderId);
    }


//    @RequestMapping("apiUpdateOrder")
//    public Integer apiUpdateOrder(tOrder tOrder){
//        return orderService.apiUpdateOrder(tOrder);
//    }
    @RequestMapping("updateOrder")
    public Integer updateOrder(tOrder order){
        return  orderService.updateOrder(order);
    }

    @RequestMapping("deleteOrder")
    public Integer deleteOrder(Long orderId){
        return  orderService.deleteOrder(orderId);
    }



}

