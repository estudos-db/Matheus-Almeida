package com.db.livraria.mapper;

import com.db.livraria.dto.CadastroAutor;
import com.db.livraria.model.Autor;

public interface AutorMapper {

    static Autor toAutor(CadastroAutor autorCadastrado){
        return Autor.builder()
                .nome(autorCadastrado.getNome())
                .genero(autorCadastrado.getGenero())
                .anoNascimento(autorCadastrado.getAnoNascimento())
                .cpf(autorCadastrado.getCpf())
                .build();
    }
}
