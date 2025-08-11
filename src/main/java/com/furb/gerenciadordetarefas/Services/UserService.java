package com.furb.gerenciadordetarefas.Services;

import com.furb.gerenciadordetarefas.Models.Users;
import com.furb.gerenciadordetarefas.Repository.UserRepository;
import com.furb.gerenciadordetarefas.dto.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Users> findAll(){return userRepository.findAll();}

    public Optional<Users> findById(int id){return userRepository.findById(id);}

    public Users findByUserName(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }

    public Users registerUsers(UserRegisterDTO userDTO){
        Users user = new Users();
        user.setUserName(userDTO.getUserName());
        user.setUserPassword(bCryptPasswordEncoder.encode(userDTO.getUserPassword()));
        user.setUserEmail(userDTO.getUserEmail());
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    public void deleteById(int id){userRepository.deleteById(id);}

    public Optional<Users> update(Users user) {
        if (userRepository.existsById(user.getUserId())) {
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }
    public Users updatePassword(Users user, String currentPassword, String newPassword) {
        if (!bCryptPasswordEncoder.matches(currentPassword, user.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Senha atual incorreta");
        }

        // Evita senha igual à anterior
        if (bCryptPasswordEncoder.matches(newPassword, user.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A nova senha não pode ser igual à anterior");
        }

        // Valida força da senha
        if (newPassword.length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha deve ter pelo menos 8 caracteres");
        }
        user.setUserPassword(bCryptPasswordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

}
