package br.com.db.api.service;

import br.com.db.api.dto.AtualizarEndereco;
import br.com.db.api.dto.CadastroEndereco;
import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.model.Pessoa;
import br.com.db.api.repository.PessoaRepository;
import br.com.db.api.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PessoaServiceTest {

    @MockBean
    private PessoaRepository pessoaRepository;
    @MockBean
    private Pessoa pessoa;
    @MockBean
    private AtualizarPessoas pessoaEnderecoAtualizado;

    @Autowired
    private PessoaServiceImpl pessoaService;

    @BeforeEach
    void init(){
        pessoa = new Pessoa(new CadastroPessoa(
                "João",
                LocalDate.of(2002,11,15),
                "2633386385",
                new CadastroEndereco("95095321", "")));
        pessoaEnderecoAtualizado = new AtualizarPessoas(
                1L,
                "Matheus",
                LocalDate.of(2002,11,15),
                new AtualizarEndereco(
                        "95095321",
                        "Dr Oscar Serafini"));
    }

    @Test
    void deveSalvarPessoa(){
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        pessoaService.salvar(new CadastroPessoa(
                "João",
                LocalDate.of(2002,11,15),
                "2633386385",
                new CadastroEndereco("95095321", "")));

                assertNotNull(pessoa);
    }
    @Test
    void deveRetornarUmaListaDePessoas(){
        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        List<ListagemPessoas> listagemPessoas = pessoaService.listarPessoas();
        assertNotNull(listagemPessoas);
    }
    @Test
    void deveRetornarPessoaPorId(){
        when(pessoaRepository.getReferenceById(1L)).thenReturn(pessoa);

        ListagemPessoas listagemPessoas = pessoaService.listarPessoaPorId(1L);
        assertNotNull(listagemPessoas);
    }
    @Test
    void deveRetornarPessoaPorCEP(){
        when(pessoaRepository.findByEnderecoCep("95095321")).thenReturn(List.of(pessoa));

        List<ListagemPessoas> listaDePessoasPorCep = pessoaService.listarPorCep("95095321");
        assertNotNull(listaDePessoasPorCep);
    }
    @Test
    void deveAtualizarPessoa(){
        when(pessoaRepository.getReferenceById(1L)).thenReturn(pessoa);

        pessoaService.atualizar(pessoaEnderecoAtualizado);

        assertNotNull(pessoaEnderecoAtualizado);
    }

    @Test
    void deveDeletarPessoa(){
        when(pessoaRepository.getReferenceById(1L)).thenReturn(pessoa);

        pessoaService.deletar(1L);
        pessoa = null;
        assertNull(pessoa);
    }

}
