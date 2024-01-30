package br.com.desafio02bootcamp.services;

import br.com.desafio02bootcamp.entities.TransacaoEntity;
import br.com.desafio02bootcamp.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<TransacaoEntity> todasAsTransacoes() {
        return transacaoRepository.findAll();
    }

    public List<TransacaoEntity> puxarTransacoesPorCategoria(Integer id){
        return transacaoRepository.findByCategoriaId(id);
    }

    public void inserirOuAtualizarTransacao(TransacaoEntity transacao) {
        transacaoRepository.save(transacao);
    }

    public void deletarTransacao(Integer id) {
        transacaoRepository.deleteById(id);
    }

    public void deletarTodasAsTransacoes(TransacaoEntity transacao) {
        transacaoRepository.deleteAll();
    }
}
