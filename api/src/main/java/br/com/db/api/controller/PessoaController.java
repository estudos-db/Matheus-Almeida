package br.com.db.api.controller;

import br.com.db.api.pessoa.AtualizarPessoas;
import br.com.db.api.pessoa.CadastroPessoa;
import br.com.db.api.pessoa.ListagemPessoas;
import br.com.db.api.service.PessoaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/cadastrar")
    @Transactional
    public void salvar(@RequestBody @Valid CadastroPessoa pessoa){
        pessoaService.salvar(pessoa);
    }

    @GetMapping("/listar")
    public List<ListagemPessoas> listarPessoas(){
       return pessoaService.listarPessoas();
    }
    @GetMapping("/listar/{id}")
    public ListagemPessoas listarPorId(@PathVariable Long id){
        return pessoaService.listarPessoaPorId(id);
    }
    @GetMapping("/listarPessoas")
    public List<ListagemPessoas> listarPorCep(@RequestParam String cep){
        return pessoaService.listarPorCep(cep);
    }
    @PutMapping("/atualizar")
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarPessoas pessoas){
        pessoaService.atualizar(pessoas);
    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        pessoaService.deletar(id);
    }

}
