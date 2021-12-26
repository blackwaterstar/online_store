package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.Orderinfo;
import com.halcyon.online_store.entity.ProductInfo;
import com.halcyon.online_store.service.OrderinfoService;
import com.halcyon.online_store.service.ProductInfoService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@RestController
@RequestMapping("//productInfo")
public class ProductInfoController {
    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private OrderinfoService orderinfoService;

    @RequestMapping("listProductInfo")
    public List<ProductInfo> listProductInfo(){
//       这几获得当前用户的当前购物车信息（所有）
        return productInfoService.listProductInfo();
    }

    @RequestMapping("selectProductInfo")
    public ProductInfo selectProductInfo(long ppid){
//       这几获得当前用户的当前购物车信息（所有）
        return productInfoService.selectProductInfo(ppid);
    }

    @RequestMapping("selectListProductInfo")
    public List<ProductInfo> selectListProductInfo(long pid){
//       这几获得当前用户的当前购物车信息（所有）
        return productInfoService.selectListProductInfo(pid);
    }

    @RequestMapping("addProductInfo")
    public int addProductInfo(ProductInfo productInfo){
//       这几获得当前用户的当前购物车信息（所有）
        return productInfoService.addProductInfo(productInfo);
    }
    @RequestMapping("deleteProductInfo")
    public int deleteProductInfo(long ppid){
//       这几获得当前用户的当前购物车信息（所有）
        return productInfoService.deleteProductInfo(ppid);
    }
    @RequestMapping("updateProductInfo")
    public int updateProductInfo(ProductInfo productInfo){
//       这几获得当前用户的当前购物车信息（所有）
        return productInfoService.updateProductInfo(productInfo);
    }

    @RequestMapping("salesProduct")
    public int salesProduct(Long ppid){

        return orderinfoService.salesProduct(ppid);
    }

}

