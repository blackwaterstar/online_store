package com.halcyon.online_store.controller;


import com.halcyon.online_store.entity.Wallet;

import com.halcyon.online_store.service.WalletService;
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
@RequestMapping("//wallet")
public class WalletController {

    @Resource
    private WalletService walletService;

    @RequestMapping("selectWallet")
    public Wallet selectWallet(Long userId){
        return walletService.selectWallet(userId);
    }
    @RequestMapping("listWallet")
    public List<Wallet> listWallet(){
        return walletService.listWallet();
    }
    @RequestMapping("topUp")
    public int topUp(Long userId,Long money){
        return walletService.topUp(userId,money);
    }
    @RequestMapping("cost")
    public int cost(Long userId,Long money){
        return walletService.cost(userId,money);
    }

}

