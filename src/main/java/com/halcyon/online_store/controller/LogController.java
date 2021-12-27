package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.Log;
import com.halcyon.online_store.service.LogService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@RequestMapping("//log")
public class LogController {

    @Resource
    private LogService logService;

    @RequestMapping("getList")
    public List<Log> getList(){
        return logService.getList();
    }

    @RequestMapping("getListByUserId")
    public List<Log> getListByUserId(Long userId){
        return logService.getListByUserId(userId);
    }

    @RequestMapping("getListByState")
    public List<Log> getListByState(Long stste){
        return logService.getListByState(stste);
    }


}

