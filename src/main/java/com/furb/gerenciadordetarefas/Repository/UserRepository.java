package com.furb.gerenciadordetarefas.Repository;

import com.furb.gerenciadordetarefas.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByUserName(String userName);
}
