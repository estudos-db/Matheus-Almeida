package com.db.livraria.service.impl;

import com.db.livraria.dto.CadastroAutor;
import com.db.livraria.model.Autor;
import com.db.livraria.repository.AutorRepository;
import com.db.livraria.repository.LivroRepository;
import com.db.livraria.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //TODO:revisar pois esta errado
        Autor autorEntity = toAutor(autorCadastrado);
        livroRepository.saveAll(autorEntity.getLivros());
        autorRepository.save(autorEntity);
        return autorEntity;
    }

    @Override
    public Autor buscarAutorPeloNome(String nome) {
        return autorRepository.findByNome(nome);
    }

    @Override
    public void deletarAutorPorId(Long id) {
        //TODO: Criar exception de NotFoundException
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));
        //TODO:Criar exception caso autor tenha um livro atrelado ao nome dele
        if (autor.getLivros().size() > 0){
            throw new RuntimeException("Autor tem livros atrelados a ele, remova os livros antes de remover autor");
        }

        autorRepository.deleteById(id);
    }
}
