package com.halcyon.online_store.mapper;

import com.halcyon.online_store.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface LogMapper extends BaseMapper<Log> {
    int getallmonthlog(int num);
}
