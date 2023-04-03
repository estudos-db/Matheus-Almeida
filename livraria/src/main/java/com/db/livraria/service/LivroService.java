package com.db.livraria.service;

import com.db.livraria.dto.request.CadastroLivro;
import com.db.livraria.model.Livro;

import java.util.List;

public interface LivroService {
    Livro salvar(CadastroLivro cadastroLivro);
    List<Livro> buscarLivrosDisponiveisParaAlugar();
    List<Livro> buscarLivrosAlugados();
    Livro buscarLivroPorId(Long id);
    List<Livro> buscarLivroPorAutor(String nome);
    void deletarLivroPorId(Long id);
}
