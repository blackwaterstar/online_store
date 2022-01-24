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
    public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long commentId;

    private Long ppid;

    private Integer userId;

    private String content;

    private String state;

    private int likes;

    private String createtime;

    private String updatime;

  private Long orderId;


}
