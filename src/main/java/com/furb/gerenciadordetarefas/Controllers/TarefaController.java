package com.furb.gerenciadordetarefas.Controllers;

import com.furb.gerenciadordetarefas.Models.StatusTarefa;
import com.furb.gerenciadordetarefas.Models.Tarefas;
import com.furb.gerenciadordetarefas.Models.Users;
import com.furb.gerenciadordetarefas.Services.TarefaService;
import com.furb.gerenciadordetarefas.Services.UserService;
import com.furb.gerenciadordetarefas.dto.StatusUpdateDTO;
import com.furb.gerenciadordetarefas.dto.TarefaDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Tarefas dos usuários")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private UserService userService;

    @GetMapping("/tarefas")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<Tarefas>> getTarefas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.findByUserName(username);
        List<Tarefas> tarefas = tarefaService.findByUser(user);
        return ResponseEntity.ok(tarefas);
    }

    @PostMapping("/tarefas")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Tarefas> setTarefas(@RequestBody TarefaDTO tarefaDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.findByUserName(username);
        Tarefas tarefa = new Tarefas();
        tarefa.setUser(user);
        tarefa.setTarefaName(tarefaDTO.getNome());
        tarefa.setTarefaStatus(StatusTarefa.NAO_INICIADA);
        return ResponseEntity.ok(tarefaService.save(tarefa));

    }
    @PutMapping("/tarefas/{id}/status")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Tarefas> updateStatusTarefa(
            @PathVariable int id,
            @RequestBody StatusUpdateDTO statusDTO) {

        // Pegar usuário logado
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.findByUserName(username);
        Tarefas tarefa = tarefaService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarefa não encontrada"));
        if (!Objects.equals(tarefa.getUser().getUserId(), user.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        tarefa.setTarefaStatus(statusDTO.getStatus());
        tarefaService.save(tarefa);

        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/tarefas/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deleteTarefa(@PathVariable int id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.findByUserName(username);
        Tarefas tarefa = tarefaService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarefa não encontrada"));
        if (!Objects.equals(tarefa.getUser().getUserId(), user.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        tarefaService.delete(tarefa);
        return ResponseEntity.noContent().build();

    }
}
