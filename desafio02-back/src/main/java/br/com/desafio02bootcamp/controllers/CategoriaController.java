package br.com.desafio02bootcamp.controllers;

import br.com.desafio02bootcamp.entities.CategoriaEntity;
import br.com.desafio02bootcamp.services.CategoriaService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaEntity> buscarCategorias() {
        return categoriaService.buscarTodasAsCategorias();
    }

    @PostMapping
    public ResponseEntity inserirCategoria(@RequestBody CategoriaEntity categoria) {
        if(categoria.getId() == null){
            categoriaService.inserirOuAtualizarCategorias(categoria);
            return ResponseEntity.status(200).build();
        } else{
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping
    public ResponseEntity atualizarCategoria(@RequestBody CategoriaEntity categoria) {
        categoriaService.inserirOuAtualizarCategorias(categoria);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping
    public void deletarCategoria(@PathParam("id") Integer id) {
        categoriaService.deletarCategoria(id);
    }

}
