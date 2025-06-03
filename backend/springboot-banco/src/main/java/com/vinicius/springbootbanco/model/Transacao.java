package com.vinicius.springbootbanco.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transacao_id")
    private Long transacaoId;

    @Column(name = "conta_origem")
    private Long contaOrigem;

    @Column(name = "conta_destino")
    private Long contaDestino;

    private Double valor;

    @Column(name = "data_transacao")
    private LocalDateTime dataTransacao = LocalDateTime.now();

    private String tipo;

    private Boolean suspeito = false;

    // Getters e Setters
    public Long getTransacaoId() {
        return transacaoId;
    }

    public Long getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Long contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Long getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Long contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getSuspeito() {
        return suspeito;
    }

    public void setSuspeito(Boolean suspeito) {
        this.suspeito = suspeito;
    }
}