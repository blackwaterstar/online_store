package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.Product;
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
public interface ProductService extends IService<Product> {

    boolean addProduct(Product product);

    boolean deleteProductByid(String pid);

    boolean updateProduct(Product product);

    List<Product> searchProduct(String pname);

    Integer deleteProductByList(List pids);

    List<Product> select(List<Long> pids);

    Product getProductById(Long ppId);

    List<Product> listProduct();
}
