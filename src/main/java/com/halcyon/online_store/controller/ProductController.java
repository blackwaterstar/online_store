package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.Product;
import com.halcyon.online_store.entity.dto.AllProductDTO;
import com.halcyon.online_store.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
@RequestMapping("//product")
public class ProductController {

    @Resource
    private ProductService productService;

    @RequestMapping("addProduct")
    public int addProduct(Product product){
        return productService.addProduct(product);
    }

    @RequestMapping("deleteProduct")
    public boolean deleteProduct(String pid){
        return productService.deleteProductByid(pid);
    }

    @RequestMapping("deleteUserByIdList")
    public Integer deleteUserByIdList(String pidList) {
        String tuIdListSub = pidList.substring(0, pidList.length()-1);
        List pids = new ArrayList();
        for (String pidStr: tuIdListSub.split(",")){
            pids.add(pidStr.trim());
        }
        return productService.deleteProductByList(pids);
    }

    @RequestMapping("updateProduct")
    public boolean updateProduct(Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping("searchProduct")
    public List<Product> searchProduct(String pname){
        return productService.searchProduct(pname);
    }

    @RequestMapping("listProduct")
    public List<Product> listProduct(){
        return productService.listProduct();
    }

    @RequestMapping("allProduct")
    public List<AllProductDTO> allProduct(){
        return productService.allProduct();
    }


}


