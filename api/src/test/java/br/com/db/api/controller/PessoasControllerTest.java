package br.com.db.api.controller;

import br.com.db.api.dto.AtualizarEndereco;
import br.com.db.api.dto.CadastroEndereco;
import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.service.impl.PessoaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoasControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private PessoaServiceImpl pessoaService;

    private CadastroPessoa cadastroPessoa;

    private ListagemPessoas listagemPessoas;

    @BeforeEach
    void init() {
        cadastroPessoa = new CadastroPessoa(
                "João",
                LocalDate.of(2022, 11, 15),
                "32711792048", List.of(new CadastroEndereco("95095123",
                "")));
        listagemPessoas = new ListagemPessoas(
                "João",
                LocalDate.of(2022, 11, 15),
                "32711792048");
    }

    @Test
    void deveCadastrarUmaPessoa() throws Exception {
        String pessoaAsJson = mapper.writeValueAsString(cadastroPessoa);

        mockMvc.perform(post("/pessoa")
                        .content(pessoaAsJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void deveListarPessoa() throws Exception {
        when(pessoaService.buscarPessoas()).thenReturn(List.of(listagemPessoas));

        mockMvc.perform(get("/pessoa")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarPessoaPorId() throws Exception {
        when(pessoaService.buscarPessoaPorId(1L)).thenReturn(listagemPessoas);

        mockMvc.perform(get("/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarPessoaPorCep() throws Exception {
        when(pessoaService.buscarPessoasPorCep("95095123")).thenReturn(List.of(listagemPessoas));

        mockMvc.perform(get("/pessoa?cep=95095123")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveAlterarPessoa() throws Exception {
        String pessoaAsJson = mapper.writeValueAsString(new AtualizarPessoas(1L, "João", LocalDate.of(2022, 11, 15), new AtualizarEndereco(1L,"95095321", "")));

        mockMvc.perform(MockMvcRequestBuilders.put("/pessoa")
                .content(pessoaAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    void deveDeletarPessoa() throws Exception {
        mockMvc.perform(delete("/pessoa/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
