package com.vinicius.springbootbanco.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vinicius.springbootbanco.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByUsuarioEmailAndSenha(String email, String senha);
    Optional<Conta> findByUsuarioEmail(String email);
}