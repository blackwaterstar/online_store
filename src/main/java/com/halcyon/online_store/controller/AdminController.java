package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.dto.LoginDTO;
import com.halcyon.online_store.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@RestController
@RequestMapping("//admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @RequestMapping("login")
    public int login(String username, String password){
        return adminService.login(username,password);
    }

}

