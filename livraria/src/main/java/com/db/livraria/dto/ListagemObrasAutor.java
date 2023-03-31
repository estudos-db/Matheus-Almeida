package com.db.livraria.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@Builder
public class ListagemObrasAutor {
    private String nome;
    private String isbn;
    private LocalDate dataPublicacao;
}
