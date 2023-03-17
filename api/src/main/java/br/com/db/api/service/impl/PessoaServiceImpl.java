package br.com.db.api.service.impl;

import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.model.Endereco;
import br.com.db.api.model.Pessoa;
import br.com.db.api.repository.EnderecoRepository;
import br.com.db.api.repository.PessoaRepository;
import br.com.db.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.db.api.mapping.PessoaMapping.atualizarPessoa;
import static br.com.db.api.mapping.PessoaMapping.converterPessoa;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
    }
    @Override
    public Pessoa salvar(CadastroPessoa pessoa) {
        Pessoa pessoaEntity = converterPessoa(pessoa);
        return pessoaRepository.save(pessoaEntity);
    }

    @Override
    public List<ListagemPessoas> buscarPessoas() {
        return pessoaRepository.findAll().stream().map(ListagemPessoas::new).collect(Collectors.toList());
    }
    @Override
    public ListagemPessoas buscarPessoaPorId(Long idPessoa) {
        return new ListagemPessoas(pessoaRepository.findById(idPessoa).get());
    }
    @Override
    public List<ListagemPessoas> buscarPessoasPorCep(String cep) {
        return pessoaRepository.findByEnderecoCep(cep).stream().map(ListagemPessoas::new).toList();
    }
    @Override
    public ListagemPessoas atualizar(AtualizarPessoas pessoa) {
        Pessoa pessoaId = pessoaRepository.getReferenceById(pessoa.id());
        Endereco endereco = enderecoRepository.getReferenceById(pessoa.endereco().id());

        Pessoa pessoaAtualizada = atualizarPessoa(pessoa,endereco, pessoaId);
        return new ListagemPessoas(pessoaAtualizada);
    }
    @Override
    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }
}
