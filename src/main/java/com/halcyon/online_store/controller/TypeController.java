package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.Type;
import com.halcyon.online_store.service.TypeService;
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
@RequestMapping("//type")
public class TypeController {

    @Resource
    private TypeService typeService;

    @RequestMapping("addType")
    public int addType(Type type){
        return typeService.addType(type);
    }
    @RequestMapping("deleteType")
    public int deleteType(Long tid){
        return typeService.deleteType(tid);
    }
    @RequestMapping("updateType")
    public int updateType(Type type){
        return typeService.updateType(type);
    }

    @RequestMapping("selectType")
    public Type selectType(Long id){
        return typeService.selectType(id);
    }
    @RequestMapping("listType")
    public List<Type> listType(){
        return typeService.listType();
    }

}

