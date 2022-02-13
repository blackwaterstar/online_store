package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.halcyon.online_store.entity.dto.LoginDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface AdminService extends IService<Admin> {

    int login(String username, String password);
}
