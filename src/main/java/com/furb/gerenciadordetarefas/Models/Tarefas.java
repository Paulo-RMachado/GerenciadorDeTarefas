package com.furb.gerenciadordetarefas.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "tarefa")
public class Tarefas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tarefasId;
    @Column(nullable = false)
    private String tarefaName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTarefa tarefaStatus;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
}
