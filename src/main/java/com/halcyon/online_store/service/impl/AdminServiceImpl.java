package com.halcyon.online_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.entity.Admin;
import com.halcyon.online_store.entity.Log;
import com.halcyon.online_store.entity.User;
import com.halcyon.online_store.entity.Wallet;
import com.halcyon.online_store.entity.dto.LoginDTO;
import com.halcyon.online_store.mapper.AdminMapper;
import com.halcyon.online_store.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private  AdminMapper adminMapper;

    @Override
    public int login(String username, String password) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username));
        if (password.equals(admin.getPassword())) {
            return 1;
        }else return 0;
    }
}
