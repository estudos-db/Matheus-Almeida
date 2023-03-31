package com.db.livraria.service.impl;

import com.db.livraria.dto.CadastroAluguel;
import com.db.livraria.exception.NotFoundException;
import com.db.livraria.model.Aluguel;
import com.db.livraria.model.Livro;
import com.db.livraria.model.Locatario;
import com.db.livraria.repository.AluguelRepository;
import com.db.livraria.repository.LivroRepository;
import com.db.livraria.repository.LocatarioRepository;
import com.db.livraria.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.db.livraria.mapper.AluguelMapper.toAluguel;

@Service
public class AluguelServiceImpl implements AluguelService {

    private final AluguelRepository aluguelRepository;
    private final LocatarioRepository locatarioRepository;
    private final LivroRepository livroRepository;
    @Autowired
    public AluguelServiceImpl(AluguelRepository aluguelRepository, LocatarioRepository locatarioRepository, LivroRepository livroRepository) {
        this.aluguelRepository = aluguelRepository;
        this.locatarioRepository = locatarioRepository;
        this.livroRepository = livroRepository;
    }

    @Override
    public Aluguel salvar(CadastroAluguel cadastroAluguel) {
        List<Livro> livrosAchados = livroRepository.findAllById(cadastroAluguel.getIdLivros());
        livrosAchados.forEach(v -> v.setAlugado(true));

        Locatario locatarioEncontrado = locatarioRepository.findById(cadastroAluguel.getIdLocatario())
                .orElseThrow(() -> new NotFoundException("Id não encontrado"));

        Aluguel aluguel = toAluguel(livrosAchados, locatarioEncontrado);
        aluguelRepository.save(aluguel);

        return aluguel;
    }
}
