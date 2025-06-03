package com.vinicius.springbootbanco.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chave_email")
public class ChaveEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chave_email_id")
    private Long id;

    private String email;

    @Column(name = "conta_id")
    private Long contaId;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    private Boolean suspeito;

    // Getters e Setters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getContaId() { return contaId; }
    public void setContaId(Long contaId) { this.contaId = contaId; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public Boolean getSuspeito() { return suspeito; }
    public void setSuspeito(Boolean suspeito) { this.suspeito = suspeito; }
}
