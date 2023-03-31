package com.db.livraria.service;

import com.db.livraria.dto.CadastroAutor;
import com.db.livraria.dto.ListagemObrasAutor;
import com.db.livraria.model.Autor;
import com.db.livraria.model.Livro;

import java.util.List;

public interface AutorService {
    Autor salvar(CadastroAutor autorCadastrado);
    Autor buscarAutorPeloNome(String nome);
    void deletarAutorPorId(Long id);

    List<ListagemObrasAutor> buscarObrasDoAutorPeloNome(String nome);
}
