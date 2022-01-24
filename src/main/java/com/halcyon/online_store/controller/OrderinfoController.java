package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.Comment;
import com.halcyon.online_store.entity.Orderinfo;
import com.halcyon.online_store.service.OrderService;
import com.halcyon.online_store.service.OrderinfoService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@RestController
@RequestMapping("//orderinfo")
public class OrderinfoController {

    @Resource
    private OrderinfoService orderinfoService;

    @RequestMapping("salesProduct")
    public int salesProduct(Long ppid){
        return orderinfoService.salesProduct(ppid);
    }




}

