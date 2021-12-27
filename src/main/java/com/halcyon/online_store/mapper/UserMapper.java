package com.halcyon.online_store.mapper;

import com.halcyon.online_store.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halcyon.online_store.entity.dto.LoginDTO;
import com.halcyon.online_store.entity.vo.LoginVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface UserMapper extends BaseMapper<User> {
    List<LoginVo> listUserDto();

}
