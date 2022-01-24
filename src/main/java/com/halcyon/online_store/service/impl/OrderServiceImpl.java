package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.halcyon.online_store.entity.*;
import com.halcyon.online_store.entity.dto.CreateOrderDTO;
import com.halcyon.online_store.entity.dto.OrderDTO;
import com.halcyon.online_store.entity.dto.OrderProductDTO;
import com.halcyon.online_store.entity.dto.ResultDTO;
import com.halcyon.online_store.entity.vo.OrderVO;
import com.halcyon.online_store.mapper.CartMapper;
import com.halcyon.online_store.mapper.OrderMapper;
import com.halcyon.online_store.mapper.OrderinfoMapper;
import com.halcyon.online_store.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, tOrder> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderinfoMapper orderinfoMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private OrderinfoService orderInfoService;

    @Resource
    private AddressService addressService;



    /**
     *
     *  往订单表里填数据
     *  往订单详情表里填数据
     *  往已售商品表里填数据
     *
     *
     */
    //方法抛出异常，就会回滚，数据库里面的数据也会回滚。
    @Transactional(rollbackFor = Exception.class)  //方法A
    public void addOrder(OrderVO orderVO) {
        //从orderVO中获得order对象
        tOrder tOrder = orderVO.getTOrder();
        List<Long> pcounts = orderVO.getPcounts();
        //从orderVO中获得所有商品的集合
        List<ProductInfo> productInfos = orderVO.getProductInfos();
        //将order存到数据库的订单表里
        orderMapper.insert(tOrder);
        //====================

        //将数据存入到订单详情表中
        //需要封装一个List<TOrderinfo>
        //将List<TProduct>==> List<TOrderinfo>
        //1.遍历
        Iterator<ProductInfo> iterator = productInfos.iterator();
        Iterator<Long> iterator1 = pcounts.iterator();
        List<Orderinfo> list = new ArrayList<Orderinfo>();
        Long orderId = tOrder.getOrderId();
        while(iterator.hasNext()){
            ProductInfo productInfo = iterator.next();
            Long pcount = iterator1.next();
            Orderinfo orderinfo = new Orderinfo();
            orderinfo.setOrderId(orderId);
            orderinfo.setPpid(Long.valueOf(productInfo.getPpid()));
            orderinfo.setPcount(pcount);
            list.add(orderinfo);

        }
        try {
            orderInfoService.addOrderInfo(list); //方法B  出现了异常
            cartMapper.deleteById(tOrder.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 收到一个OrderDTO,转换成OrderVO，再调用addOrder
     */
    public ResultDTO addOrder(OrderDTO orderDTO) {

        ResultDTO resultDTO = new ResultDTO();
        try {
            //1.封装OrderVO中的TOrder
            OrderVO orderVO = new OrderVO();

            tOrder tOrder = new tOrder();
            QueryWrapper<tOrder> wrapper = new QueryWrapper<>();
            wrapper.select("max(order_id) as maxid");
            Map<String,Object> map = this.getMap(wrapper);
            long maxPid = (long) map.get("maxid");
            tOrder.setOrderId(maxPid+1);
            tOrder.setUserId(orderDTO.getUserId());
            tOrder.setOrderUser(orderDTO.getOrderUser());
            tOrder.setOrderTel(orderDTO.getOrderTel());
            tOrder.setOrderAddr(orderDTO.getOrderAddr());
            tOrder.setOrderPrice(orderDTO.getOrderPrice());
            tOrder.setPaystatue("未付款");
            //orderVO.settOrder(tOrder);
            orderVO.setTOrder(tOrder);
            //2.封装OrderVO中的List<Products>
//        orderVO.setProducts();
            List<Long> ppids = orderDTO.getPpids();
            List<Long> pcounts = orderDTO.getPcounts();
            //根据商品id集合获得相应的商品集合
            List<ProductInfo> productInfos = productInfoService.selectListbypids(ppids);
            orderVO.setProductInfos(productInfos);
            orderVO.setPcounts(pcounts);
            addOrder(orderVO);
            resultDTO.setResult(true);
            resultDTO.setMessage("下单成功");

        } catch (Exception e) {
            e.printStackTrace();
            resultDTO.setResult(false);
            resultDTO.setMessage("你点背，下单失败");
        }
        return resultDTO;
    }
    /**
     * 获取当前用户的所有订单
     *
     */
    public List<CreateOrderDTO> getList(Long userId) {
        QueryWrapper<tOrder> wrapper = new QueryWrapper<>();
        wrapper.eq(" user_id",userId);
        List<tOrder> tOrders = orderMapper.selectList(wrapper);
        List<CreateOrderDTO> cods = new ArrayList<CreateOrderDTO>();

        tOrders.forEach(order -> {
            CreateOrderDTO cod = new CreateOrderDTO();
            cod.setOrderId(order.getOrderId());
            BigDecimal bd = new BigDecimal(order.getOrderPrice().longValue());
            cod.setOrderPrice(bd);
            //将cod存入到cods集合中
            cods.add(cod);
        });

        //遍历cods
        //根据订单编号，去订单详情表中获取该订单的所欲商品的id及商品数量
        //还要根据商品的id取商品表里获取商品名称和商品价格

        cods.forEach( cod->{
            //根据订单编号，去订单详情表中获取该订单的所有商品的id及商品数量
            List<Orderinfo> orderinfos =  orderInfoService.getOrderInfosByOrderId(cod.getOrderId());
            //封装OrderProductDTO
            List<OrderProductDTO> opds = new ArrayList<>();
            orderinfos.forEach(orderinfo ->{
                //通过商品id封装OrderProductDTO
                OrderProductDTO opd = getOrderProductDTO(orderinfo.getPpid());
                //从orderinfo中获取商品数量存入到opd中
                opd.setPcount(orderinfo.getPcount());
                //opd封装完毕
                //存入到集合中
                opds.add(opd);
            });

            //将商品集合存入到CreateOrderDTO对象中
            cod.setProducts(opds);
//            getOrderProductDTO()

        });
        return cods;
    }

    /**
     * 根据商品id，去数据库获取该商品的信息，并封装成OrderProductDTO对象
     */
    private OrderProductDTO getOrderProductDTO(Long ppid) {
        OrderProductDTO opd = new OrderProductDTO();
        ProductInfo productInfo = productInfoService.selectProductInfo(ppid);
        opd.setPname(productInfo.getPname());
        opd.setPrice(productInfo.getPrice());
        opd.setPpid(ppid);
        return opd;

    }



    @Override
    public List<CreateOrderDTO> getList2(Long orderId, Long userId) {
         /*
    订单的创建时间
    订单编号
    订单总金额

    商品集合：
        商品名称
        商品价格
        商品数量
     */
        QueryWrapper<tOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.like("order_id",orderId);
        List<tOrder> tOrders = orderMapper.selectList(wrapper);

        List<CreateOrderDTO> cods = new ArrayList<CreateOrderDTO>();
        tOrders.forEach(order -> {
            CreateOrderDTO cod = new CreateOrderDTO();
            cod.setOrderId(order.getOrderId());
            BigDecimal bd = new BigDecimal(order.getOrderPrice().longValue());
            cod.setOrderPrice(bd);
            //将cod存入到cods集合中
            cods.add(cod);
        });

        //遍历cods
        //根据订单编号，从订单详情表中获取该订单的所欲商品的id及商品数量
        //根据商品的id取商品表里获取商品名称和商品价格

        cods.forEach( cod->{
            //根据订单编号，去订单详情表中获取该订单的所有商品的id及商品数量
            List<Orderinfo> orderinfos =  orderInfoService.getOrderInfosByOrderId(cod.getOrderId());
            //封装OrderProductDTO
            List<OrderProductDTO> opds = new ArrayList<>();
            orderinfos.forEach(orderinfo ->{
                //通过商品id封装DTO
                OrderProductDTO opd = getOrderProductDTO(orderinfo.getPpid());
                //从orderinfo中获取商品数量存入到opd中
                opd.setPcount(orderinfo.getPcount());
                //opd封装完毕
                //存入到集合中
                opds.add(opd);
            });

            //将商品集合存入到CreateOrderDTO对象中
            cod.setProducts(opds);
//            getOrderProductDTO()

        });
        return cods;
    }

    @Override
    public tOrder getorder(Long orderId){
        return  orderMapper.selectOne(new QueryWrapper<tOrder>().eq("order_id",orderId));
    }

    @Override
    public Integer updatestatme(Long orderId) {
        List<Orderinfo> orderinfos = orderInfoService.list(new QueryWrapper<Orderinfo>().eq("order_id", orderId));
        orderinfos.forEach(orderinfo->{
            ProductInfo productInfo = productInfoService.selectProductInfo(orderinfo.getPpid());
            int number= (int) ((productInfo.getPnumber()-orderinfo.getPcount()));
            productInfo.setPnumber(number);
            int allnumber= (int) ((productInfo.getAllsalenumber()+orderinfo.getPcount()));
            productInfo.setAllsalenumber(allnumber);
            productInfoService.updateProductInfo(productInfo);

        });
        tOrder tOrder = getorder(orderId);
        tOrder.setPaystatue("已付款");
        return orderMapper.updateById(tOrder);
    }

}
