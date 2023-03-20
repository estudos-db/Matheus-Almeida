package br.com.db.api.service;

import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.exception.NotFoundException;
import br.com.db.api.model.Pessoa;

import java.util.List;

public interface PessoaService {

    Pessoa salvar(CadastroPessoa pessoa);
    List<ListagemPessoas> buscarPessoas();
    ListagemPessoas buscarPessoaPorId(Long id) throws NotFoundException;
    List<ListagemPessoas> buscarPessoasPorCep(String cep);
    ListagemPessoas atualizar(AtualizarPessoas pessoas);
    void deletar(Long id);
}
