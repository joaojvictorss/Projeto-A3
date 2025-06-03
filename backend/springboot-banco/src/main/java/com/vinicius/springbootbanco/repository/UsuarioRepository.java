package com.vinicius.springbootbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vinicius.springbootbanco.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}