package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.vo.CartVO;
import com.halcyon.online_store.service.CartService;
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
@RequestMapping("//cart")
public class CartController {

    @Resource
    private CartService cartService;


    @RequestMapping("cartList")
    public List<CartVO> getCartList(Long userId){
        return cartService.getCartVOList(userId);
    }


    @RequestMapping("getUserCart")
    public List<CartVO> getUserCart(Long userId){
//       获得当前用户的当前购物车信息（所有）
        return cartService.list(userId);

    }

    @RequestMapping("deleteCart")
    public int deleteCart(Long cartId){
//       获得当前用户的当前购物车信息（所有）
        return cartService.deleteCart(cartId);

    }

    @RequestMapping("deleteListCart")
    public int deleteListCart(List cartIds){
//       获得当前用户的当前购物车信息（所有）
        return cartService.deleteListCart(cartIds);

    }

    @RequestMapping("addCart")
    public void addCart(Long userId,Long pid,Long pcount){
//       获得当前用户的当前购物车信息（所有）
        cartService.addCart(userId,pid,pcount);

    }
}

