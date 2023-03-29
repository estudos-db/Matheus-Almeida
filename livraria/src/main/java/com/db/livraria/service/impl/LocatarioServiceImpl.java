package com.db.livraria.service.impl;

import com.db.livraria.dto.CadastroLocatario;
import com.db.livraria.exception.NotFoundException;
import com.db.livraria.mapper.LocatarioMapper;
import com.db.livraria.model.Locatario;
import com.db.livraria.repository.LocatarioRepository;
import com.db.livraria.service.LocatarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.db.livraria.mapper.LocatarioMapper.*;

@Service
public class LocatarioServiceImpl implements LocatarioService {

    private final LocatarioRepository locatarioRepository;
    @Autowired
    public LocatarioServiceImpl(LocatarioRepository locatarioRepository) {
        this.locatarioRepository = locatarioRepository;
    }

    @Override
    public Locatario salvar(CadastroLocatario locatarioCadastrado) {
        Locatario locatario = toLocatario(locatarioCadastrado);
        locatarioRepository.save(locatario);
        return locatario;
    }

    @Override
    public Locatario buscarLocatarioPorId(Long id) {
        return locatarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado"));
    }

    @Override
    public Locatario buscarLocatarioPeloNome(String nome) {
        return locatarioRepository.findByNome(nome).orElseThrow(() -> new NotFoundException("Nome não encontrado"));
    }
}
