package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.entity.Order;
import com.halcyon.online_store.entity.Orderinfo;
import com.halcyon.online_store.mapper.OrderinfoMapper;
import com.halcyon.online_store.service.OrderinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class OrderinfoServiceImpl extends ServiceImpl<OrderinfoMapper, Orderinfo> implements OrderinfoService {
    @Resource
    OrderinfoMapper orderinfoMapper;


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void addOrderInfo(List<Orderinfo> orderinfos) throws Exception {

        if(orderinfos.isEmpty() || orderinfos==null) {
            throw new NullPointerException();
        }else{
            for (Orderinfo orderinfo : orderinfos) {
                orderinfoMapper.insert(orderinfo);
            }
        }
    }

    @Override
    public List<Orderinfo> getOrderInfosByOrderId(Long orderId) {
        QueryWrapper<Orderinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id",orderId);
        return orderinfoMapper.selectList(wrapper);
    }



}
