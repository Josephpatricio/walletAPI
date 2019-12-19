package br.com.wallet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    @Length(min = 3, max = 60, message = "O nome dever conter entre 3 e 60 caracteres.")
    private String name;
    @Email(message = "Email invalido")
    private String email;
    @NotNull
    @Length(min = 6, message = "A senha deve conter no minimo 6 caracteres.")
    private String password;
}