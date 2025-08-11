package com.furb.gerenciadordetarefas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TarefaDTO {
    @NotBlank(message = "Username é obrigatório")
    private String nome;
}
