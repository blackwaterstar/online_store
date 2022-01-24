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
    public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Long pid;

    private String pimg;

    private String pname;

    private Long price;

      /**
     * 详细分类号
     */
      private Integer ppid;

      /**
     * 库存
     */
      private Integer pnumber;

     /**总销量
     * *
     */
       private Integer allsalenumber;


}
