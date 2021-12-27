package com.halcyon.online_store.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author Halcyon
 * @date 2021/12/27
 * @apiNote
 */
@Data
public class LoginVo {
    private Integer id;

    private Long userId;

    private String username;

    private String password;

    private String address;

    private String detailAddress;

    private String telephone;

    private String email;

    private String createtime;

    private String updatime;

    private Long wId;

    private Long userAmount;

    /**
     * 冻结金额，微信后台接收到再释放
     */
    private Long userFzamount;

    /**
     * 从创建到现在的总消费
     */
    private Long userConsume;
}
