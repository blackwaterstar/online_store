package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.Product;
import com.halcyon.online_store.entity.dto.AllProductDTO;
import com.halcyon.online_store.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    //添加书籍
    @RequestMapping("addProduct")
    public int addProduct(Product product){
        return productService.addProduct(product);
    }

    //根据id删除商品单个书籍
    @RequestMapping("deleteProduct")
    public boolean deleteProduct(String pid){
        return productService.deleteProductByid(pid);
    }

    //根据id集合删除多个书籍
    @RequestMapping("deleteUserByIdList")
    public Integer deleteUserByIdList(String pidList) {
        String tuIdListSub = pidList.substring(0, pidList.length()-1);
        List pids = new ArrayList();
        for (String pidStr: tuIdListSub.split(",")){
            pids.add(pidStr.trim());
        }
        return productService.deleteProductByList(pids);
    }

    //更新书籍
    @RequestMapping("updateProduct")
    public boolean updateProduct(Product product){
        return productService.updateProduct(product);
    }

    //根据名称查找书籍
    @RequestMapping("searchProduct")
    public List<Product> searchProduct(String pname){
        return productService.searchProduct(pname);
    }

    //根据名称查找书籍后封装数据
    @RequestMapping("searchProduct1")
    public List<AllProductDTO> searchProductThenBuilt(String pname){
        return productService.searchProductThenBuilt(pname);
    }

    //显示所有书籍
    @RequestMapping("listProduct")
    public List<Product> listProduct(){
        return productService.listProduct();
    }

    //封装数据后返回所有与书籍有关的信息
    @RequestMapping("allProduct")
    public List<AllProductDTO> allProduct(){
        return productService.allProduct();
    }

    //根据id查找书籍
    @RequestMapping("findProductByPid")
    public Product findProductByPid(long pid){
        return productService.findProductByPid(pid);
    }

    //根据书籍详细id查找书籍
    @RequestMapping("findProductByPpid")
    public Product findProductByPpid(long ppid){
        return productService.findProductByPpid(ppid);
    }


}


