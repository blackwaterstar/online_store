package com.halcyon.online_store.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Halcyon
 * @date 2022/4/1
 * @apiNote
 */
@Data
public class saleInfoDTO {
    private List newVisitis;
    private List messages;
    private List purchases;
    private List shoppings;
}
