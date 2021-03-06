package br.com.wallet.service;

import br.com.wallet.domain.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    Optional<User> findByEmail(String email);
}