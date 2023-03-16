package br.com.db.api.service;

import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.model.Pessoa;

import java.util.List;

public interface PessoaService {

    Pessoa salvar(CadastroPessoa pessoa);
    List<ListagemPessoas> listarPessoas();
    ListagemPessoas listarPessoaPorId(Long id);
    List<ListagemPessoas> listarPorCep(String cep);
    void atualizar(AtualizarPessoas pessoas);
    void deletar(Long id);
}
