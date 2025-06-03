package com.vinicius.springbootbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vinicius.springbootbanco.model.ChaveEmail;
import java.util.Optional;

public interface ChaveEmailRepository extends JpaRepository<ChaveEmail, Long> {
    Optional<ChaveEmail> findByEmail(String email);
}
