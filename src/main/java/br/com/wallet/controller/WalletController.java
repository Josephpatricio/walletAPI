package br.com.wallet.controller;

import br.com.wallet.domain.Wallet;
import br.com.wallet.dto.WalletDTO;
import br.com.wallet.response.Response;
import br.com.wallet.service.WalletService;
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
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletService service;

    @PostMapping
    public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO walletDTO, BindingResult result) {
        Response<WalletDTO> response = new Response<WalletDTO>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Wallet wallet = service.save(this.convertDtoToEntity(walletDTO));
        response.setData(this.convertEntityToDto(wallet));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Wallet convertDtoToEntity(WalletDTO dto) {
        Wallet wallet = new Wallet();
        wallet.setId(dto.getId());
        wallet.setName(dto.getName());
        wallet.setValue(dto.getValue());
        return wallet;
    }

    private WalletDTO convertEntityToDto(Wallet wallet) {
        WalletDTO WalletDto = new WalletDTO();
        WalletDto.setId(wallet.getId());
        WalletDto.setName(wallet.getName());
        WalletDto.setValue(wallet.getValue());
        return WalletDto;
    }
}