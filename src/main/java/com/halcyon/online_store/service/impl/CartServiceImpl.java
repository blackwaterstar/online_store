package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.common.util.MyUtil;
import com.halcyon.online_store.entity.Cart;
import com.halcyon.online_store.entity.vo.CartVO;
import com.halcyon.online_store.mapper.CartMapper;
import com.halcyon.online_store.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Resource
    private CartMapper cartMapper;

    public List<Cart> getAllCarts(Long userId) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return cartMapper.selectList(wrapper);
    }

    public List<CartVO> getCartVOList(Long userId){

        return cartMapper.selectCartVOByUserId(userId);

    }

    public List<CartVO> list(Long userId) {
        //获取当前购车的CartVO集合
        return getCartVOList(userId);

    }


    public void list1(Long userId, Long ppid, Long pcount) {
        //1.获取所有的购物车信息
        List<Cart> carts = getAllCarts(userId);


        //2.更新购物车表
        boolean b = true;
        for (Cart cart : carts) {
            //如果商品已存在，让已存在的商品的数量改变，并更新进数据库中。
            if(cart.getPpid().equals(ppid)){
                cart.setPcount(cart.getPcount()+pcount);
                cartMapper.updateById(cart);
                b = false;
            }
        }
        //如果商品不存在。则添加新的记录
        if(b){
            //封装tcart
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setPcount(pcount);
            cart.setCartId(MyUtil.getCurrentTimeForId());
            cart.setPpid(ppid);
            //存到数据库里
            cartMapper.insert(cart);
        }

    }

    @Override
    public int deleteCart(Long cartId) {
        return cartMapper.deleteById(cartId);
    }

    @Override
    public int deleteListCart(List cartIds) {
        return cartMapper.deleteBatchIds(cartIds);
    }

}
