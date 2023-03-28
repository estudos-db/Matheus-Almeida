package com.db.livraria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO: validar tada retirada
    private LocalDate dataRetirada = LocalDate.now();
    //TODO: calcular data de devolução com a data de retirada
    private LocalDate dataDevolucao;
    @OneToMany
    @JoinTable(name = "aluguel_livro", joinColumns = @JoinColumn(name = "aluguel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id", referencedColumnName = "id"))
    private List<Livro> livros;
    @OneToOne
    private Locatario locatario;
}
