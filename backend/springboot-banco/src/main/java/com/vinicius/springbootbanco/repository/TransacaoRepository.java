package com.vinicius.springbootbanco.repository;

import com.vinicius.springbootbanco.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaOrigemOrContaDestino(Long contaOrigem, Long contaDestino);
}
