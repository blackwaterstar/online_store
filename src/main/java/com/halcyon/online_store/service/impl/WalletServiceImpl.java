package com.halcyon.online_store.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halcyon.online_store.entity.Wallet;
import com.halcyon.online_store.mapper.WalletMapper;
import com.halcyon.online_store.service.WalletService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements WalletService {

    @Resource
    private WalletMapper walletMapper;


    @Override
    public Wallet selectWallet(Long userId) {
        return walletMapper.selectOne(new QueryWrapper<Wallet>().eq("user_id",userId));
    }

    @Override
    public List<Wallet> listWallet() {
        return walletMapper.selectList(null);
    }

    @Override
    public int topUp(Long userId,Long money){
        Wallet wallet=selectWallet(userId);
        wallet.setUserAmount(wallet.getUserAmount()+money);
        return walletMapper.updateById(wallet);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cost(Long userId, Long money) {
        Wallet wallet=selectWallet(userId);
        if(wallet.getUserAmount()>=money){
            wallet.setUserAmount(wallet.getUserAmount()-money);
            wallet.setUserConsume(wallet.getUserConsume()+money);
            walletMapper.updateById(wallet);
            return 1;
        }
        return 0;
    }
}
