package br.com.db.api.controller;

import br.com.db.api.endereco.AtualizarEndereco;
import br.com.db.api.endereco.CadastroEndereco;
import br.com.db.api.pessoa.AtualizarPessoas;
import br.com.db.api.pessoa.CadastroPessoa;
import br.com.db.api.pessoa.ListagemPessoas;
import br.com.db.api.service.PessoaService;
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
@ExtendWith(MockitoExtension.class)
public class PessoasControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CadastroPessoa cadastroPessoa;

    @Mock
    private ListagemPessoas listagemPessoas;

    @MockBean
    private PessoaService pessoaService;

    private final ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void init() {
        cadastroPessoa = new CadastroPessoa("João", LocalDate.of(2022, 11, 15), "32711792048", new CadastroEndereco("95095123", ""));
        listagemPessoas = new ListagemPessoas("João", LocalDate.of(2022, 11, 15), "32711792048");
    }

    @Test
    void deveCadastrarUmaPessoa() throws Exception {

        mapper.findAndRegisterModules();
        String pessoaAsJson = mapper.writeValueAsString(cadastroPessoa);

        mockMvc.perform(post("/pessoas/cadastrar").content(pessoaAsJson).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void deveListarPessoa() throws Exception {

        when(pessoaService.listarPessoas()).thenReturn(List.of(listagemPessoas));

        mapper.findAndRegisterModules();

        mockMvc.perform(get("/pessoas/listar").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void deveListarPessoaPorId() throws Exception {

        when(pessoaService.listarPessoaPorId(1L)).thenReturn(listagemPessoas);

        mapper.findAndRegisterModules();

        mockMvc.perform(get("/pessoas/listar/1").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void deveListarPessoaPorCep() throws Exception {

        when(pessoaService.listarPorCep("95095123")).thenReturn(List.of(listagemPessoas));

        mapper.findAndRegisterModules();

        mockMvc.perform(get("/pessoas/listarPessoas?cep=95095123").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void deveAlterarPessoa() throws Exception {
        mapper.findAndRegisterModules();
        String pessoaAsJson = mapper.writeValueAsString(new AtualizarPessoas(1L, "João", LocalDate.of(2022, 11, 15), new AtualizarEndereco("95095321", "")));

        mockMvc.perform(MockMvcRequestBuilders.put("/pessoas/atualizar").content(pessoaAsJson).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

    }

    @Test
    void deveDeletarPessoa() throws Exception {
        mockMvc.perform(delete("/pessoas/deletar/1").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

    }

}
