package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface LogService extends IService<Log> {

    List<Log> getList();

    List<Log> getListByUserId(Long userId);

    List<Log> getListByState(Long stste);

}
