package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.halcyon.online_store.entity.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface CartService extends IService<Cart> {
    List<Cart> getAllCarts(Long userId);

    List<CartVO> getCartVOList(Long userId);


    List<CartVO> list(Long userId);



    void list1(Long userId, Long pid, Long pcount);

    int deleteCart(Long cartId);

    int deleteListCart(List cartIds);
}
