package br.com.wallet.controller;

import br.com.wallet.domain.User;
import br.com.wallet.domain.UserWallet;
import br.com.wallet.domain.Wallet;
import br.com.wallet.dto.UserWalletDTO;
import br.com.wallet.response.Response;
import br.com.wallet.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user-wallet")
public class UserWalletController {

    @Autowired
    private UserWalletService service;

    @PostMapping
    public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result) {
        Response<UserWalletDTO> response = new Response<UserWalletDTO>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        UserWallet userWallet = service.save(this.convertDtoToEntity(dto));
        response.setData(this.convertEntityToDto(userWallet));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public UserWallet convertDtoToEntity(UserWalletDTO dto) {
        UserWallet userWallet = new UserWallet();
        User user = new User();
        user.setId(dto.getUsers());
        Wallet wallet = new Wallet();
        wallet.setId(dto.getWallet());

        userWallet.setId(dto.getId());
        userWallet.setUsers(user);
        userWallet.setWallet(wallet);
        return userWallet;
    }

    public UserWalletDTO convertEntityToDto(UserWallet userWallet){
        UserWalletDTO dto = new UserWalletDTO();
        dto.setId(userWallet.getId());
        dto.setUsers(userWallet.getUsers().getId());
        dto.setWallet(userWallet.getWallet().getId());
        return dto;
    }
}