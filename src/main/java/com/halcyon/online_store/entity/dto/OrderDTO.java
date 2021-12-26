package com.halcyon.online_store.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 存放提交订单时的请求参数
 */
@Data
public class OrderDTO {

    private Long userId;
    private Long orderPrice;
    private String orderUser;
    private String orderTel;
    private String orderAddr;
    private List<Long> ppids;
    private List<Long> pcounts;

}
