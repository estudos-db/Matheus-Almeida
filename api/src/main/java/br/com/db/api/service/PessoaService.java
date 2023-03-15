package br.com.db.api.service;

import br.com.db.api.pessoa.AtualizarPessoas;
import br.com.db.api.pessoa.CadastroPessoa;
import br.com.db.api.pessoa.ListagemPessoas;
import br.com.db.api.pessoa.Pessoa;
import br.com.db.api.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void salvar(CadastroPessoa pessoa) {
        pessoaRepository.save(new Pessoa(pessoa));
    }

    public List<ListagemPessoas> listarPessoas() {
        return pessoaRepository.findAll().stream().map(ListagemPessoas::new).collect(Collectors.toList());
    }

    public ListagemPessoas listarPessoaPorId(Long id) {
        return new ListagemPessoas(pessoaRepository.getReferenceById(id));
    }

    public List<ListagemPessoas> listarPorCep(String cep) {
        return pessoaRepository.findByEnderecoCep(cep).stream().map(ListagemPessoas::new).collect(Collectors.toList());
    }

    public void atualizar(AtualizarPessoas pessoas) {
        Pessoa pessoaId = pessoaRepository.getReferenceById(pessoas.id());
        pessoaId.atualizar(pessoas);
    }

    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }
}
