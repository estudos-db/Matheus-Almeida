package com.db.livraria.service.impl;

import com.db.livraria.dto.CadastroLivro;
import com.db.livraria.model.Autor;
import com.db.livraria.model.Livro;
import com.db.livraria.repository.AutorRepository;
import com.db.livraria.repository.LivroRepository;
import com.db.livraria.service.LivroService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.db.livraria.mapper.LivroMapper.*;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroServiceImpl(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public Livro salvar(CadastroLivro cadastroLivro) {
        Livro livroEntity = toLivro(cadastroLivro);
        livroRepository.save(livroEntity);
        return livroEntity;
    }

    @Override
    public List<Livro> buscarLivrosDisponiveis() {
        return null;
    }

    @Override
    public List<Livro> buscarLivrosAlugados() {
        return null;
    }

    @Override
    public Livro buscarLivroPorId() {
        return null;
    }

    @Override
    public List<Livro> buscarLivroPorAutor() {
        return null;
    }
}
