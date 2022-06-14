package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.entity.Orderinfo;
import com.halcyon.online_store.entity.Product;
import com.halcyon.online_store.entity.ProductInfo;
import com.halcyon.online_store.entity.dto.AllProductDTO;
import com.halcyon.online_store.mapper.ProductInfoMapper;
import com.halcyon.online_store.mapper.ProductMapper;
import com.halcyon.online_store.service.OrderinfoService;
import com.halcyon.online_store.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductInfoMapper productInfoMapper;


    @Override
    public int addProduct(Product product) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.select("max(pid) as maxPid");
        Map<String,Object> map = this.getMap(wrapper);
        long maxPid = (long) map.get("maxPid");
        product.setPid((int) (maxPid+1));
        return productMapper.insert(product);
    }

    @Override
    public boolean deleteProductByid(String pid) {
        return productMapper.deleteById(pid)==1;
    }

    @Override
    public boolean updateProduct(Product product) {
        return productMapper.updateById(product)==1;
    }

    @Override
    public List<Product> searchProduct(String pname) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.like("pname",pname);
        return productMapper.selectList(wrapper);

    }

    @Override
    public Integer deleteProductByList(List pids) {
        return productMapper.deleteBatchIds(pids);
    }

    @Override
    public List<Product> select(List<Long> pids) {
        return productMapper.selectBatchIds(pids);
    }

    @Override
    public Product getProductById(Long ppId) {
        return productMapper.selectById(ppId);
    }

    @Override
    public List<Product> listProduct() {
        return productMapper.selectList(null);
    }

    @Override
    public List<AllProductDTO> allProduct() {
        List<Product> products = productMapper.selectList(null);
        List<AllProductDTO> list = new ArrayList<>();
        products.forEach(product -> {
            List<ProductInfo> productInfos = productInfoMapper.selectList(new QueryWrapper<ProductInfo>().
                    eq("pid", product.getPid()));
            AllProductDTO productDTO = new AllProductDTO();
            productDTO.setPid(product.getPid());
            productDTO.setPname(product.getPname());
            productDTO.setTid(product.getTid());
            productDTO.setTpId(product.getTpId());
            productDTO.setProductInfos(productInfos);
            list.add(productDTO);
        });
        return list;
    }

    @Override
    public List<AllProductDTO> searchProductThenBuilt(String pname) {
        List<Product> products = productMapper.selectList(new QueryWrapper<Product>().like("pname",pname));
        List<AllProductDTO> list = new ArrayList<>();
        products.forEach(product -> {
            List<ProductInfo> productInfos = productInfoMapper.selectList(new QueryWrapper<ProductInfo>().
                    eq("pid", product.getPid()));
            AllProductDTO productDTO = new AllProductDTO();
            productDTO.setPid(product.getPid());
            productDTO.setPname(product.getPname());
            productDTO.setTid(product.getTid());
            productDTO.setTpId(product.getTpId());
            productDTO.setProductInfos(productInfos);
            list.add(productDTO);
        });
        return list;
    }

    @Override
    public Product findProductByPid(long pid) {
        return productMapper.selectOne(new QueryWrapper<Product>().eq("pid",pid));
    }

    @Override
    public Product findProductByPpid(long ppid) {
        return productMapper.selectOne(new QueryWrapper<Product>().eq("pid",ppid/100));
    }



}
