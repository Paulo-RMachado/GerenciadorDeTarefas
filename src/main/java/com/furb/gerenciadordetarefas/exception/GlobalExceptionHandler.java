package com.furb.gerenciadordetarefas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleEnumError(HttpMessageNotReadableException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("erro", "Valor inválido para um campo. Verifique se o status da tarefa está correto.");
        response.put("valoresAceitos", "NAO_INICIADA, EM_ANDAMENTO, COMPLETA");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
