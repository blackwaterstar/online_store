package com.halcyon.online_store.mapper;

import com.halcyon.online_store.entity.Orderinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface OrderinfoMapper extends BaseMapper<Orderinfo> {

    int getallmonthsalenum(int num);

}
