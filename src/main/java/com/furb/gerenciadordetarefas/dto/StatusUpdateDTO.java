package com.furb.gerenciadordetarefas.dto;

import com.furb.gerenciadordetarefas.Models.StatusTarefa;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusUpdateDTO {
    @NotNull
    private StatusTarefa status;
}
