package com.vinicius.springbootbanco.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contaId;

    // Faz referência ao usuário
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "numero_conta", unique = true, length = 6)
    private String numeroConta;

    // Propriedade para a senha (armazenada em formato hasheado)
    private String senha;

    private Double saldo;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    private Boolean suspeito;

    // Construtor
    public Conta() {
    }

    // Getters e Setters
    public Long getContaId() {
        return contaId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getSuspeito() {
        return suspeito;
    }

    public void setSuspeito(Boolean suspeito) {
        this.suspeito = suspeito;
    }
}