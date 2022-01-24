package com.halcyon.online_store.service;

import com.halcyon.online_store.entity.Wallet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Halcyon
 * @since 2021-12-09
 */
public interface WalletService extends IService<Wallet> {

    Wallet selectWallet(Long userId);

    List<Wallet> listWallet();

    int topUp(Long userId,Long money);

    int cost(Long userId, Long money);
}
