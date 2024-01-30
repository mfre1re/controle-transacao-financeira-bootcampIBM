package br.com.desafio02bootcamp.repositories;

import br.com.desafio02bootcamp.entities.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Integer> {
    List<TransacaoEntity> findByCategoriaId(Integer categoriaId);

}
