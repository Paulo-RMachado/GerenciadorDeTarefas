package com.furb.gerenciadordetarefas.Services;

import com.furb.gerenciadordetarefas.Models.Tarefas;
import com.furb.gerenciadordetarefas.Models.Users;
import com.furb.gerenciadordetarefas.Repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefasRepository tarefaRepository;

    public List<Tarefas> findAll(){
        return tarefaRepository.findAll();
    }
    public Optional<Tarefas> findById(Integer id){
        return tarefaRepository.findById(id);
    }
    public List<Tarefas> findByUser(Users user){
        return tarefaRepository.findByUser(user);
    }
    public Tarefas save(Tarefas tarefa){
        return tarefaRepository.save(tarefa);
    }
    public void delete(Tarefas tarefa){
        tarefaRepository.delete(tarefa);
    }
    public Optional<Tarefas> update(Tarefas tarefa) {
        if (tarefaRepository.existsById(tarefa.getTarefasId())) {
            return Optional.of(tarefaRepository.save(tarefa));
        }
        return Optional.empty();
    }
}
