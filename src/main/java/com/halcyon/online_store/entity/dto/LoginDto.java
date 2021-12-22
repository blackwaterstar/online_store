package com.halcyon.online_store.entity.dto;

import com.halcyon.online_store.entity.User;
import lombok.Data;

/**
 * @author Halcyon
 * @date 2021/12/22
 * @apiNote
 */
@Data
public class LoginDto {
    private User user;
    private Long userAmount;
    private Long userConsume;
}
