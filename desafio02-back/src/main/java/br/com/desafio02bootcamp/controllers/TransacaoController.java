package br.com.desafio02bootcamp.controllers;

import br.com.desafio02bootcamp.entities.TransacaoEntity;
import br.com.desafio02bootcamp.services.CategoriaService;
import br.com.desafio02bootcamp.services.TransacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "transacao", produces = {"application/json"})
@Tag(name = "API Transacao Financeira")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<TransacaoEntity> buscarTransacao(){
        return transacaoService.todasAsTransacoes();
    }

    @GetMapping("somartransacoes")
    public Object somarTransacoesCategoria(@PathParam("id") Integer id){
        System.out.println(id);
        if(id == null){
            return ResponseEntity.status(500).build();
        } else{
            List<TransacaoEntity> transacoes = transacaoService.puxarTransacoesPorCategoria(id);
            double somaContas = 0;

            for (TransacaoEntity transacao : transacoes) {
                somaContas += transacao.getValor();
            }
            return somaContas;
        }
    }

    @PostMapping("inserir")
    public ResponseEntity inserirTransacao(@RequestBody List<TransacaoEntity> transacoes){
        if(transacoes.size() == 0) {
            return ResponseEntity.status(400).build();
        }

        for(TransacaoEntity transacao : transacoes){
            transacaoService.inserirOuAtualizarTransacao(transacao);
        }
        return ResponseEntity.status(200).build();
    }

    @PostMapping("atualizar")
    public ResponseEntity atualizarTransacao(@RequestBody TransacaoEntity transacao){
        if(transacao.getId() == null){
            return ResponseEntity.status(500).build();
        } else{
            transacaoService.inserirOuAtualizarTransacao(transacao);
            return ResponseEntity.status(200).build();
        }
    }

    @DeleteMapping()
    public void deletarTransacao(@PathParam("id") Integer id) {
        transacaoService.deletarTransacao(id);
    }

    @DeleteMapping("apagartudo")
    public void deletarTransacao(TransacaoEntity transacao) {
        transacaoService.deletarTodasAsTransacoes(transacao);
    }
    
}

