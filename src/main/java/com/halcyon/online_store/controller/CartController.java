package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.vo.CartVO;
import com.halcyon.online_store.service.CartService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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


    @RequestMapping("addCart")
    public List<CartVO> addCart(Long userId){
//       这几获得当前用户的当前购物车信息（所有）
        return cartService.list(userId);

    }

    @RequestMapping("deleteCart")
    public int deleteCart(Long cartId){
//       这几获得当前用户的当前购物车信息（所有）
        return cartService.deleteCart(cartId);

    }

    @RequestMapping("deleteListCart")
    public int deleteListCart(List cartIds){
//       这几获得当前用户的当前购物车信息（所有）
        return cartService.deleteListCart(cartIds);

    }

    @RequestMapping("addCart1")
    public void addCart1(Long userId,Long pid,Long pcount){
//       这几获得当前用户的当前购物车信息（所有）
        cartService.list1(userId,pid,pcount);

    }
}

