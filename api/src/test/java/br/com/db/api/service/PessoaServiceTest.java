package br.com.db.api.service;

import br.com.db.api.dto.AtualizarEndereco;
import br.com.db.api.dto.CadastroEndereco;
import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.exception.NotFoundException;
import br.com.db.api.model.Endereco;
import br.com.db.api.model.Pessoa;
import br.com.db.api.repository.EnderecoRepository;
import br.com.db.api.repository.PessoaRepository;
import br.com.db.api.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class PessoaServiceTest {

    @InjectMocks
    private PessoaServiceImpl pessoaService;
    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private EnderecoRepository enderecoRepository;
    private Pessoa pessoa;
    private Endereco endereco;
    private AtualizarPessoas pessoaEnderecoAtualizado;

    @BeforeEach
    void init(){
        openMocks(this);
        iniciarVariaveis();
    }

    @Test
    void deveSalvarPessoa(){
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        pessoaService.salvar(new CadastroPessoa(
                "João",
                LocalDate.of(2002,11,15),
                "2633386385",
                List.of(new CadastroEndereco("95095321", ""))));

                assertNotNull(pessoa);
    }
    @Test
    void deveRetornarUmaListaDePessoas(){
        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        List<ListagemPessoas> listagemPessoas = pessoaService.buscarPessoas();
        assertNotNull(listagemPessoas);
    }
    @Test
    void deveRetornarPessoaPorId() throws NotFoundException {
       given(pessoaRepository.findById(anyLong())).willReturn(Optional.of(pessoa));

        ListagemPessoas listagemPessoas = pessoaService.buscarPessoaPorId(2L);
        verify(pessoaRepository, times(1)).findById(anyLong());

    }

    @Test
    void deveRetornarExceptionQuandoNãoEncontrarId() throws NotFoundException {
        given(pessoaRepository.findById(anyLong())).willAnswer(valor -> {throw new NotFoundException("id não encontrado");});

        assertThrows(NotFoundException.class, () -> pessoaService.buscarPessoaPorId(1L));

    }
    @Test
    void deveRetornarPessoaPorCEP(){
        when(pessoaRepository.findByEnderecosCep("95095321")).thenReturn(List.of(pessoa));

        pessoaService.buscarPessoasPorCep("95095321");

        verify(pessoaRepository, times(1)).findByEnderecosCep("95095321");
    }
    @Test
    void deveAtualizarPessoa(){
        when(pessoaRepository.getReferenceById(anyLong())).thenReturn(pessoa);
        when(enderecoRepository.getReferenceById(anyLong())).thenReturn(endereco);

        pessoaService.atualizar(pessoaEnderecoAtualizado);

        verify(pessoaRepository, times(1)).getReferenceById(anyLong());
        verify(enderecoRepository, times(1)).getReferenceById(anyLong());
    }

    @Test
    void deveDeletarPessoa(){
        when(pessoaRepository.getReferenceById(1L)).thenReturn(pessoa);

        pessoaService.deletar(1L);
        pessoa = null;
        assertNull(pessoa);
    }

    void iniciarVariaveis(){
        endereco = Endereco.builder()
                .id(1L)
                .cep("95095321")
                .rua("Dr Oscar Serafini").build();

        pessoa = Pessoa.builder()
                .id(1L)
                .nome("João")
                .dataNascimento(LocalDate.of(2002,11,15))
                .cpf("33322211199")
                .enderecos(List.of(endereco))
                .build();
        pessoaEnderecoAtualizado = new AtualizarPessoas(
                1L,
                "João",
                LocalDate.of(2002,11,15),
                new AtualizarEndereco(1L,
                        "95095321",
                        "Dr Oscar Seraifini"));
    }

}
