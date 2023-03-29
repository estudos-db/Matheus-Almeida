package com.db.livraria.controller;

import com.db.livraria.dto.CadastroAutor;
import com.db.livraria.model.Autor;
import com.db.livraria.service.impl.AutorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Year;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AutorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AutorServiceImpl autorService;
    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();
    private CadastroAutor cadastroAutor;

    @BeforeEach
    void init(){
        cadastroAutor = CadastroAutor.builder()
                .nome("João")
                .genero("Masculino")
                .anoNascimento(Year.of(2010))
                .cpf("03234536007")
                .build();
    }

    @Test
    void deveCadastrarAutor() throws Exception {
        String autorAsJson = objectMapper.writeValueAsString(cadastroAutor);

        mockMvc.perform(post("/autor")
                .content(autorAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }
    @Test
    void deveRetornarAutorAoBuscarPeloNomeNoParametro() throws Exception {
        when(autorService.buscarAutorPeloNome(any(String.class))).thenReturn(any(Autor.class));

        mockMvc.perform(get("/autor?nome=Joao")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
    @Test
    void deveDeletarUmAutor() throws Exception {

        mockMvc.perform(delete("/autor/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }



}
