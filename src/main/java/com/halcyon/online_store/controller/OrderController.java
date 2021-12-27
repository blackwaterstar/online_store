package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.tOrder;
import com.halcyon.online_store.entity.dto.CreateOrderDTO;
import com.halcyon.online_store.entity.dto.OrderDTO;
import com.halcyon.online_store.entity.dto.ResultDTO;
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
        System.out.println(orderDTO);

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


//    @RequestMapping("list1")
//    public List<CreateOrderDTO> getList1(Long userId){
//        return orderService.getList1(userId);
//    }


    //根据用户账户和订单id查找订单
    @RequestMapping("list2")
    public List<CreateOrderDTO> getList2(Long orderId,Long userId){
        return orderService.getList2(orderId,userId);
    }



    @RequestMapping("getOrder")
    public tOrder getOrder(Long orderId){
        return orderService.getorder(orderId);
    }


    @RequestMapping("updatestatme")
    public Integer updatestatme(Long orderId){
        return orderService.updatestatme(orderId);
    }


}

