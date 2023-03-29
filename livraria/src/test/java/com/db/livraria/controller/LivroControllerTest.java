package com.db.livraria.controller;

import com.db.livraria.dto.CadastroLivro;
import com.db.livraria.service.impl.LivroServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LivroControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LivroServiceImpl livroService;
    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();
    private CadastroLivro cadastroLivro;

    @BeforeEach
    void init(){
        cadastroLivro = CadastroLivro.builder()
                .nome("O Minotauro")
                .isbn("9788525044297")
                .dataPublicacao(LocalDate.of(2022,11,10))
                .build();
    }

    @Test
    void deveSalvarLivro() throws Exception {
        String livroAsJson = mapper.writeValueAsString(cadastroLivro);

        mockMvc.perform(post("/livro")
                .content(livroAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }
}
