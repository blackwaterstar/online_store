package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.common.util.MyUtil;
import com.halcyon.online_store.entity.Address;
import com.halcyon.online_store.entity.Order;
import com.halcyon.online_store.entity.Orderinfo;
import com.halcyon.online_store.entity.Product;
import com.halcyon.online_store.entity.dto.CreateOrderDTO;
import com.halcyon.online_store.entity.dto.OrderDTO;
import com.halcyon.online_store.entity.dto.OrderProductDTO;
import com.halcyon.online_store.entity.dto.ResultDTO;
import com.halcyon.online_store.entity.vo.OrderVO;
import com.halcyon.online_store.mapper.CartMapper;
import com.halcyon.online_store.mapper.OrderMapper;
import com.halcyon.online_store.service.AddressService;
import com.halcyon.online_store.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.online_store.service.OrderinfoService;
import com.halcyon.online_store.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private ProductService productService;

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
        Order order = orderVO.getTOrder();
        List<Long> pcounts = orderVO.getPcounts();
        //从orderVO中获得所有商品的集合
        List<Product> products = orderVO.getProducts();
        //将order存到数据库的订单表里
        orderMapper.insert(order);
        //====================

        //将数据存入到订单详情表中
        //需要封装一个List<TOrderinfo>
        //将List<TProduct>==> List<TOrderinfo>
        //1.遍历
        Iterator<Product> iterator = products.iterator();
        Iterator<Long> iterator1 = pcounts.iterator();
        List<Orderinfo> list = new ArrayList<Orderinfo>();
        Long orderId = order.getOrderId();
        while(iterator.hasNext()){
            Product product = iterator.next();
            Long pcount = iterator1.next();
            Orderinfo orderinfo = new Orderinfo();
            orderinfo.setOrderId(orderId);
            orderinfo.setPpid(product.getPid());
            orderinfo.setPcount(pcount);
            list.add(orderinfo);
        }
        try {
            orderInfoService.addOrderInfo(list); //方法B  出现了异常
            cartMapper.deleteById(order.getUserId());
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

            Order order = new Order();

            order.setOrderId(MyUtil.getCurrentTimeForId());
            order.setUserId(orderDTO.getUserId());
            order.setOrderUser(orderDTO.getOrderUser());
            order.setOrderTel(orderDTO.getOrderTel());
            order.setOrderAddr(orderDTO.getOrderAddr());
            order.setOrderPrice(orderDTO.getOrderPrice());
            order.setPaystatue("未付款");
            //orderVO.settOrder(tOrder);
            orderVO.setTOrder(order);
            //2.封装OrderVO中的List<Products>
//        orderVO.setProducts();
            List<Long> pids = orderDTO.getPids();
            List<Long> pcounts = orderDTO.getPcounts();
            //根据商品id集合获得相应的商品集合
            List<Product> products = productService.select(pids);
            orderVO.setProducts(products);
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
        /*
    订单的创建时间
    订单编号
    订单总金额

    商品集合：
        商品名称
        商品价格
        商品数量
     */
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Order> orders = orderMapper.selectList(wrapper);


        List<CreateOrderDTO> cods = new ArrayList<CreateOrderDTO>();

        orders.forEach( order -> {
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
        Product product = productService.getProductById(ppid);
        opd.setPname(product.getPname());
        return opd;

    }

    public List<CreateOrderDTO> getList1(Long userId) {
        /*
    订单的创建时间
    订单编号
    订单总金额

    商品集合：
        商品名称
        商品价格
        商品数量
     */
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Order> orders = orderMapper.selectList(wrapper);


        List<CreateOrderDTO> cods = new ArrayList<CreateOrderDTO>();

        orders.forEach( order -> {
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
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.like("order_id",orderId);
        List<Order> orders = orderMapper.selectList(wrapper);


        List<CreateOrderDTO> cods = new ArrayList<CreateOrderDTO>();

        orders.forEach( order -> {
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

    @Override
    public Order getorder(Long orderId){
        return  orderMapper.selectById(orderId);
    }

    @Override
    public Integer updatestatme(Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setPaystatue("已付款");
        return orderMapper.updateById(order);
    }

}
