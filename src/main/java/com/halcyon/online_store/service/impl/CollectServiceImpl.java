package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.common.util.MyUtil;
import com.halcyon.online_store.entity.Cart;
import com.halcyon.online_store.entity.Collect;
import com.halcyon.online_store.entity.Log;
import com.halcyon.online_store.entity.ProductInfo;
import com.halcyon.online_store.mapper.CollectMapper;
import com.halcyon.online_store.mapper.LogMapper;
import com.halcyon.online_store.mapper.ProductInfoMapper;
import com.halcyon.online_store.service.CollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halcyon.online_store.service.LogService;
import com.halcyon.online_store.service.ProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private LogMapper logMapper;

    @Override
    public int addCollect(Long userId, Long ppid) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("ppid", ppid);
        if (collectMapper.selectCount(wrapper) > 0) {
            return 0;
        } else {
            ProductInfo productInfo = productInfoService.selectProductInfo(ppid);
            Collect collect = new Collect();
            collect.setPrice(productInfo.getPrice());
            collect.setUserId(userId);
            collect.setPpid(ppid);
            QueryWrapper<Collect> wrapper1 = new QueryWrapper<>();
            wrapper1.select("max(collect_id) as maxid");
            Map<String,Object> map = this.getMap(wrapper1);
            long maxPid = (long) map.get("maxid");
            collect.setCollectId(maxPid+1);
            Log log = new Log();
            log.setUserId(userId);
            log.setState(3);
            log.setController("用户"+userId+"收藏了"+productInfo.getPname());
            logMapper.insert(log);
            return collectMapper.insert(collect);
        }
    }

    @Override
    public int deleteCollect(Long userId, Long ppid) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("ppid", ppid);
        return collectMapper.delete(wrapper);

    }

    @Override
    public List<Collect> listCollect(Long userId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return collectMapper.selectList(wrapper);
    }
}
