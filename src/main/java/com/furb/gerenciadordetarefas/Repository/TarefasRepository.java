package com.furb.gerenciadordetarefas.Repository;

import com.furb.gerenciadordetarefas.Models.Tarefas;
import com.furb.gerenciadordetarefas.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas,Integer> {
    List<Tarefas> findByUser(Users user);
}
