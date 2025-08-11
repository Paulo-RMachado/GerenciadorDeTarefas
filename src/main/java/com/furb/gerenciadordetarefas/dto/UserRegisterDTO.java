package com.furb.gerenciadordetarefas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserRegisterDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String userName;

    @NotBlank(message = "Email é obrigatório")
    private String userEmail;

    @NotBlank(message = "Senha é obrigatória")
    private String userPassword;



}
