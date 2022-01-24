package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.Type;
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
public interface TypeService extends IService<Type> {

    int addType(Type type);

    int deleteType(Long tid);

    int updateType(Type type);

    Type selectType(Long id);

    List<Type> listType();
}
