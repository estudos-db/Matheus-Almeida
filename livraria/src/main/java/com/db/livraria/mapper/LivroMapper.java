package com.db.livraria.mapper;

import com.db.livraria.dto.CadastroLivro;
import com.db.livraria.model.Autor;
import com.db.livraria.model.Livro;

import java.util.List;

public interface LivroMapper {
    static Livro toLivro(CadastroLivro cadastroLivro, List<Autor> entidadesId) {
        return Livro.builder()
                .nome(cadastroLivro.getNome())
                .isbn(cadastroLivro.getIsbn())
                .autores(entidadesId)
                .dataPublicacao(cadastroLivro.getDataPublicacao())
                .build();
    }
}
