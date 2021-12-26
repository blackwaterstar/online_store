package com.halcyon.online_store.entity.dto;

import com.halcyon.online_store.entity.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * @author Halcyon
 * @date 2021/12/22
 * @apiNote
 */
@Data
public class AllProductDTO {

    private Long tpId;

    private String pname;

    private Long tid;

    private int pid;

    private List<ProductInfo> productInfos;
}
