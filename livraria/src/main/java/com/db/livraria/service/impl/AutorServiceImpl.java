package com.db.livraria.service.impl;

import com.db.livraria.dto.CadastroAutor;
import com.db.livraria.dto.ListagemObrasAutor;
import com.db.livraria.exception.LivroAtreladoException;
import com.db.livraria.exception.NotFoundException;
import com.db.livraria.mapper.LivroMapper;
import com.db.livraria.model.Autor;
import com.db.livraria.model.Livro;
import com.db.livraria.repository.AutorRepository;
import com.db.livraria.repository.LivroRepository;
import com.db.livraria.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.db.livraria.mapper.AutorMapper.*;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;
    @Autowired
    public AutorServiceImpl(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    @Override
    public Autor salvar(CadastroAutor autorCadastrado) {
        Autor autorEntity = toAutor(autorCadastrado);
        autorRepository.save(autorEntity);
        return autorEntity;
    }

    @Override
    public Autor buscarAutorPeloNome(String nome) {
        return autorRepository.findByNome(nome)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado"));
    }
    @Override
    public List<ListagemObrasAutor> buscarObrasDoAutorPeloNome(String nome) {
        return livroRepository.findByAutoresNome(nome).stream().map(LivroMapper::toLivro).toList();
    }

    @Override
    public void deletarAutorPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado"));

        livroRepository.findByAutoresNome(autor.getNome())
                .ifPresent(v -> {throw new LivroAtreladoException("O autor tem livros atrelados a ele, remova os livros antes de remover autor");});

        autorRepository.deleteById(id);
    }
}
