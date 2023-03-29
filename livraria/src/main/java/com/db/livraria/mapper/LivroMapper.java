package com.db.livraria.mapper;

import com.db.livraria.dto.CadastroLivro;
import com.db.livraria.model.Livro;

public interface LivroMapper {
    static Livro toLivro(CadastroLivro cadastroLivro) {
        return Livro.builder()
                .nome(cadastroLivro.getNome())
                .isbn(cadastroLivro.getIsbn())
                .dataPublicacao(cadastroLivro.getDataPublicacao())
                .autores(cadastroLivro.getAutores())
                .build();
    }
}
