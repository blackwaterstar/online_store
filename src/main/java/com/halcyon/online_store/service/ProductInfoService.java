package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface ProductInfoService extends IService<ProductInfo> {

    List<ProductInfo> listProductInfo();

    ProductInfo selectProductInfo(long ppid);

    int addProductInfo(ProductInfo productInfo);

    int deleteProductInfo(long ppid);

    int updateProductInfo(ProductInfo productInfo);

    List<ProductInfo> selectListProductInfo(long pid);

    int allsalenum();

    List<ProductInfo> selectListbypids(List<Long> ppids);

}

