package br.com.wallet.service.impl;

import br.com.wallet.domain.UserWallet;
import br.com.wallet.repository.UserWalletRepository;
import br.com.wallet.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserWalletServiceImpl implements UserWalletService {

    @Autowired
    UserWalletRepository repository;

    @Override
    public UserWallet save(UserWallet userWallet) {
        return save(userWallet);
    }
}