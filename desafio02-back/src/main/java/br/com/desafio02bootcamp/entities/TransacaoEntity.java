package br.com.desafio02bootcamp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name="transacao")
public class TransacaoEntity {

    @Id
    @Column(name = "idtransacao", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valortransacao", nullable = false)
    private double valor;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private CategoriaEntity categoria;

    @Column(name = "datatransacao", nullable = false)
    private LocalDate data;
}
