package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.halcyon.online_store.entity.Orderinfo;
import com.halcyon.online_store.entity.ProductInfo;
import com.halcyon.online_store.entity.dto.saleDTO;
import com.halcyon.online_store.mapper.OrderinfoMapper;
import com.halcyon.online_store.mapper.ProductInfoMapper;
import com.halcyon.online_store.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

    @Resource
    private ProductInfoMapper productInfoMapper;


    @Override
    public List<ProductInfo> listProductInfo() {
        return productInfoMapper.selectList(null);
    }

    @Override
    public ProductInfo selectProductInfo(long ppid) {
        QueryWrapper<ProductInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("ppid",ppid);
        return productInfoMapper.selectOne(wrapper);
    }

    @Override
    public int addProductInfo(ProductInfo productInfo) {
        int a = 1+productInfoMapper.selectCount(new QueryWrapper<ProductInfo>().eq("pid",productInfo.getPid()));
        productInfo.setPpid((int) (productInfo.getPid()*100+a));
        productInfo.setAllsalenumber(0);
        return productInfoMapper.insert(productInfo);
    }

    @Override
    public int deleteProductInfo(long ppid) {
        return productInfoMapper.deleteById(ppid);
    }

    @Override
    public int updateProductInfo(ProductInfo productInfo) {
        UpdateWrapper<ProductInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("ppid",productInfo.getPpid());
        return productInfoMapper.update(productInfo,wrapper);
    }

    @Override
    public List<ProductInfo> selectListProductInfo(long pid) {
        return productInfoMapper.selectList(new QueryWrapper<ProductInfo>().eq("pid",pid));
    }

    @Override
    public int allsalenum() {
        QueryWrapper<ProductInfo> wrapper = new QueryWrapper<>();
        wrapper.select("sum(allsalenumber) as salenumber");
        Map<String,Object> map = this.getMap(wrapper);
        BigDecimal allnum = (BigDecimal) map.get("salenumber");
        return allnum.intValue();
    }

    @Override
    public List<ProductInfo> selectListbypids(List<Long> ppids) {
        return productInfoMapper.selectList(new QueryWrapper<ProductInfo>().in("ppid",ppids));
    }

}
