package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.entity.Log;
import com.halcyon.online_store.mapper.LogMapper;
import com.halcyon.online_store.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public List<Log> getList() {
        return logMapper.selectList(null);
    }

    @Override
    public List<Log> getListByUserId(Long userId) {
        return logMapper.selectList(new QueryWrapper<Log>().eq("user_id",userId));
    }

    @Override
    public List<Log> getListByState(Long stste) {
        return logMapper.selectList(new QueryWrapper<Log>().eq("state",stste));
    }
}
