package br.com.desafio02bootcamp.services;

import br.com.desafio02bootcamp.entities.CategoriaEntity;
import br.com.desafio02bootcamp.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaEntity> buscarTodasAsCategorias() {
        return categoriaRepository.findAll();
    }

    public void inserirOuAtualizarCategorias(CategoriaEntity categoria) {
        categoriaRepository.save(categoria);
    }

    public void deletarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
