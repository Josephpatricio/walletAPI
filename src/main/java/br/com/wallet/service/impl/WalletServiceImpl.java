package br.com.wallet.service.impl;

import br.com.wallet.domain.Wallet;
import br.com.wallet.repository.WalletRepository;
import br.com.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository repository;

    @Override
    public Wallet save(Wallet wallet) {
        return repository.save(wallet);
    }
}