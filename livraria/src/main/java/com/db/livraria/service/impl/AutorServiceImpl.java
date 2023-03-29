package com.db.livraria.service.impl;

import com.db.livraria.dto.CadastroAutor;
import com.db.livraria.exception.LivroAtreladoException;
import com.db.livraria.exception.NotFoundException;
import com.db.livraria.model.Autor;
import com.db.livraria.model.Livro;
import com.db.livraria.repository.AutorRepository;
import com.db.livraria.repository.LivroRepository;
import com.db.livraria.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return autorRepository.findByNome(nome);
    }

    @Override
    public void deletarAutorPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado"));
        Optional<Livro> livroComAutor = livroRepository.findByAutoresNome(autor.getNome());


        if (livroComAutor.isPresent()){
            throw new LivroAtreladoException("O autor tem livros atrelados a ele, remova os livros antes de remover autor");
        }
        autorRepository.deleteById(id);
    }
}
