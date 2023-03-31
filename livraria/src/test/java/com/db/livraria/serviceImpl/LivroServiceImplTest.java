package com.db.livraria.serviceImpl;

import com.db.livraria.dto.CadastroLivro;
import com.db.livraria.mapper.Livro;
import com.db.livraria.repository.LivroRepository;
import com.db.livraria.service.impl.LivroServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroServiceImplTest {

    @InjectMocks
    private LivroServiceImpl livroService;
    @Mock
    private LivroRepository livroRepository;

    private Livro livro;

    @BeforeEach
    void init(){
        livro = Livro.builder()
                .id(1L)
                .nome("O Minotauro")
                .isbn("9788525044297")
                .dataPublicacao(LocalDate.of(2020,11,22))
                .alugado(false)
                .build();
    }

    @Test
    void deveSalvarLivro(){
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro livroSalvo = livroService.salvar(CadastroLivro.builder()
                .nome("O Minotauro")
                .isbn("9788525044297")
                .dataPublicacao(LocalDate.of(2020, 11, 22))
                .build());

        assertNotNull(livroSalvo);
    }

    @Test
    void deveRetornarLivrosNaoAlugados(){
        when(livroRepository.findByAlugadoFalse()).thenReturn(List.of(livro));

        livroService.buscarLivrosDisponiveisParaAlugar();

        verify(livroRepository, times(1)).findByAlugadoFalse();
    }
    @Test
    void deveRetornarLivrosPorId(){
        when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));

        livroService.buscarLivroPorId(1L);
    }

    @Test
    void deveDeletarLivro(){
        when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));

        livroService.deletarLivroPorId(1L);
    }


}
