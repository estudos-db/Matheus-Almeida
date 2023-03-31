package com.db.livraria.service.impl;

import com.db.livraria.dto.AtualizarLocatario;
import com.db.livraria.dto.CadastroLocatario;
import com.db.livraria.exception.AlugelLocatarioException;
import com.db.livraria.exception.NotFoundException;
import com.db.livraria.model.Locatario;
import com.db.livraria.repository.AluguelRepository;
import com.db.livraria.repository.LocatarioRepository;
import com.db.livraria.service.LocatarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.db.livraria.mapper.LocatarioMapper.toLocatario;

@Service
public class LocatarioServiceImpl implements LocatarioService {

    private final LocatarioRepository locatarioRepository;
    private final AluguelRepository aluguelRepository;
    @Autowired
    public LocatarioServiceImpl(LocatarioRepository locatarioRepository, AluguelRepository aluguelRepository) {
        this.locatarioRepository = locatarioRepository;
        this.aluguelRepository = aluguelRepository;
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

    @Override
    @Transactional
    public Locatario editar(Long id, AtualizarLocatario locatario) {
        Locatario locatarioEntity = locatarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado"));
        return toLocatario(locatarioEntity, locatario);
    }

    @Override
    public void deletar(Long id) {
        Locatario locatario = locatarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado"));
        aluguelRepository.findByLocatarioNome(locatario.getNome())
                .ifPresent(v ->{throw new AlugelLocatarioException("Locatario não pode ser excluido pois esta alugando livros");});

        locatarioRepository.deleteById(id);
    }
}
