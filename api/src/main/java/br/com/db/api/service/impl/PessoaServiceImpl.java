package br.com.db.api.service.impl;

import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.model.Pessoa;
import br.com.db.api.repository.PessoaRepository;
import br.com.db.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    @Override
    public Pessoa salvar(CadastroPessoa pessoa) {
       return pessoaRepository.save(new Pessoa(pessoa));
    }

    @Override
    public List<ListagemPessoas> listarPessoas() {
        return pessoaRepository.findAll().stream().map(ListagemPessoas::new).collect(Collectors.toList());
    }
    @Override
    public ListagemPessoas listarPessoaPorId(Long id) {
        return new ListagemPessoas(pessoaRepository.getReferenceById(id));
    }
    @Override
    public List<ListagemPessoas> listarPorCep(String cep) {
        return pessoaRepository.findByEnderecoCep(cep).stream().map(ListagemPessoas::new).toList();
    }
    @Override
    public void atualizar(AtualizarPessoas pessoas) {
        Pessoa pessoaId = pessoaRepository.getReferenceById(pessoas.id());
        pessoaId.atualizar(pessoas);
    }
    @Override
    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }
}
