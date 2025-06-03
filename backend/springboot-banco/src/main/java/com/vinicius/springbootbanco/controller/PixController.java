package com.vinicius.springbootbanco.controller;

import com.vinicius.springbootbanco.model.Conta;
import com.vinicius.springbootbanco.model.Transacao;
import com.vinicius.springbootbanco.repository.ContaRepository;
import com.vinicius.springbootbanco.repository.TransacaoRepository;
import com.vinicius.springbootbanco.repository.ChaveEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pix")
@CrossOrigin(origins = "*")
public class PixController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ChaveEmailRepository chaveEmailRepository;

    // Extrato Pix
    @GetMapping("/extrato/{contaId}")
    public ResponseEntity<List<Transacao>> getExtrato(@PathVariable Long contaId) {
        List<Transacao> transacoes = transacaoRepository.findByContaOrigemOrContaDestino(contaId, contaId);
        return ResponseEntity.ok(transacoes);
    }

    // Transferência Pix
    @PostMapping("/transferir")
    public ResponseEntity<String> transferirPix(
            @RequestParam Long contaOrigem,
            @RequestParam Long contaDestino,
            @RequestParam Double valor) {

        Conta origem = contaRepository.findById(contaOrigem).orElse(null);
        Conta destino = contaRepository.findById(contaDestino).orElse(null);

        if (origem == null || destino == null) {
            return ResponseEntity.badRequest().body("Conta não encontrada.");
        }
        if (origem.getSaldo() < valor) {
            return ResponseEntity.badRequest().body("Saldo insuficiente.");
        }

        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);
        contaRepository.save(origem);
        contaRepository.save(destino);

        Transacao transacao = new Transacao();
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestino(contaDestino);
        transacao.setValor(valor);
        transacao.setTipo("pix");
        transacaoRepository.save(transacao);

        return ResponseEntity.ok("Transferência Pix realizada com sucesso.");
    }

    // Verificar se a chave Pix (email) é suspeita
    @GetMapping("/verificar-chave")
    public ResponseEntity<Boolean> verificarChave(@RequestParam("chave") String chave) {
        // Considera a chave como email
        return chaveEmailRepository.findByEmail(chave)
                .map(chaveEmail -> ResponseEntity.ok(Boolean.TRUE.equals(chaveEmail.getSuspeito())))
                .orElse(ResponseEntity.status(404).body(null));
    }

    // Endpoint auxiliar: buscar conta por email (para origem)
    @GetMapping("/conta/por-email")
    public ResponseEntity<Conta> getContaPorEmail(@RequestParam String email) {
        return contaRepository.findByUsuarioEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint auxiliar: buscar conta por chave Pix (email) (para destino)
    @GetMapping("/conta/por-chave")
    public ResponseEntity<Conta> getContaPorChave(@RequestParam String chave) {
        return chaveEmailRepository.findByEmail(chave)
                .flatMap(chaveEmail -> contaRepository.findById(chaveEmail.getContaId()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}