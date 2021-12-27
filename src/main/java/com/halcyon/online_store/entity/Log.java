package com.halcyon.online_store.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer logId;

    private Long userId;

    private LocalDateTime time;

    private String controller;

    //1登录2注册3收藏4加购5支付
    private Integer state;


}
