package com.halcyon.online_store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    public class Orderinfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "toi_id", type = IdType.AUTO)
      private Long toiId;

    private Long orderId;

    private Long ppid;

    private Long pcount;


}
