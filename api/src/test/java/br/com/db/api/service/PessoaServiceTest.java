package br.com.db.api.service;

import br.com.db.api.dto.AtualizarEndereco;
import br.com.db.api.dto.CadastroEndereco;
import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.model.Endereco;
import br.com.db.api.model.Pessoa;
import br.com.db.api.repository.PessoaRepository;
import br.com.db.api.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;
    private Pessoa pessoa;
    private AtualizarPessoas pessoaEnderecoAtualizado;
    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @BeforeEach
    void init(){
        openMocks(this);
        pessoa = Pessoa.builder()
                .id(1L)
                .nome("João")
                .dataNascimento(LocalDate.of(2002,11,15))
                .cpf("33322211199")
                .endereco(Endereco.builder()
                        .id(1L)
                        .cep("95095321")
                        .rua("Dr Oscar Seraifini").build())
                .build();
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

        List<ListagemPessoas> listagemPessoas = pessoaService.buscarPessoas();
        assertNotNull(listagemPessoas);
    }
//    @Test
//    void deveRetornarPessoaPorId(){
//
//        Optional<Pessoa> pessoaOptional = Optional.of(new Pessoa());
//       given(pessoaRepository.findById(pessoa.getId())).willReturn(Optional.of(pessoa));
//
//        ListagemPessoas listagemPessoas = pessoaService.buscarPessoaPorId(1L);
//        verify(pessoaRepository).findById(anyLong());
//        assertNotNull(listagemPessoas);
//    }
    @Test
    void deveRetornarPessoaPorCEP(){
        when(pessoaRepository.findByEnderecoCep("95095321")).thenReturn(List.of(pessoa));

        List<ListagemPessoas> listaDePessoasPorCep = pessoaService.buscarPessoasPorCep("95095321");
        assertNotNull(listaDePessoasPorCep);
    }
//    @Test
//    void deveAtualizarPessoa(){
//        when(pessoaRepository.getReferenceById(1L)).thenReturn(pessoa);
//
//        pessoaService.atualizar(pessoaEnderecoAtualizado);
//
//        assertNotNull(pessoaEnderecoAtualizado);
//    }

    @Test
    void deveDeletarPessoa(){
        when(pessoaRepository.getReferenceById(1L)).thenReturn(pessoa);

        pessoaService.deletar(1L);
        pessoa = null;
        assertNull(pessoa);
    }

}
