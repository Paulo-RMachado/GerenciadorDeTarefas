package com.furb.gerenciadordetarefas.Controllers;

import com.furb.gerenciadordetarefas.Models.Users;
import com.furb.gerenciadordetarefas.Services.UserService;
import com.furb.gerenciadordetarefas.dto.UserRegisterDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Usuários")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        List<Users> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        Users createdUser = userService.registerUsers(userRegisterDTO);
        return ResponseEntity.ok(createdUser);
    }
}
