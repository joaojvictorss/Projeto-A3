package com.vinicius.springbootbanco.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vinicius.springbootbanco.model.Conta;
import com.vinicius.springbootbanco.repository.ContaRepository;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String senha) {
        System.out.println("Tentativa de login: email=" + email + ", senha=" + senha);
        try {
            // A senha já chega hasheada do front-end, não hashear novamente!
            Optional<Conta> contaOpt = contaRepository.findByUsuarioEmailAndSenha(email, senha);

            if (contaOpt.isPresent()) {
                return ResponseEntity.ok(contaOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Para logar o erro no console do servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor: " + e.getMessage());
        }
    }
}