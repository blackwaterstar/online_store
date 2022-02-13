package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.halcyon.online_store.entity.dto.LoginDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface UserService extends IService<User> {

    LoginDTO login (Long userid, String password);

    boolean register(User user);

    boolean checkUser(String userId);

    boolean modifyInfo(User user);

    List<User> searchUser(String username);

    List<User> listUser();

    int deleteUser(String userId);

    int deleteListUser(List userIds);

    List<LoginDTO> listUserDto();

    LoginDTO searchUserDto(Long userid);

    List<LoginDTO> searchUserDto1(String username);
}

