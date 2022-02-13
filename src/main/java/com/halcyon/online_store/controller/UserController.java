package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.User;
import com.halcyon.online_store.entity.dto.LoginDTO;
import com.halcyon.online_store.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@RestController
@RequestMapping("//user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("login")
    public LoginDTO login(Long userid, String password){
       return userService.login(userid,password);
    }

    @RequestMapping("listUserDto")
    public List<LoginDTO> listUserDto(){
        return userService.listUserDto();
    }

    @RequestMapping("searchUserDto")
    public LoginDTO searchUserDto(Long userid){
        return userService.searchUserDto(userid);
    }

    @RequestMapping("searchUserDto1")
    public List<LoginDTO> searchUserDto1(String username ){
        return userService.searchUserDto1(username);
    }

    @RequestMapping("register")
    public boolean register (User user){
        return userService.register(user);
    }

    @RequestMapping("checkUser")
    public boolean checkUser (String userId){
        return userService.checkUser(userId);
    }

    @RequestMapping("deleteUser")
    public int deleteUser (String userId){
        return userService.deleteUser(userId);
    }

    @RequestMapping("deleteListUser")
    public int deleteListUser (List userIds){
        return userService.deleteListUser(userIds);
    }

    @RequestMapping("modifyInfo")
    public boolean modifyInfo (User user){
        return userService.modifyInfo(user);
    }

    @RequestMapping("searchUser")
    public List<User> searchProduct(String username){
        return userService.searchUser(username);
    }

    @RequestMapping("listUser")
    public List<User> listUser(){
        return userService.listUser();
    }

}

