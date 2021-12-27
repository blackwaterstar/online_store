package com.halcyon.online_store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
    public class tOrder implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "to_id", type = IdType.AUTO)
      private Long toId;

    private Long orderId;

    private Long userId;

    private Long orderPrice;

    private String orderUser;

    private String orderTel;

    private String orderAddr;

    private String createdTime;

   private String updatedTime;

    private String paystatue;


}
