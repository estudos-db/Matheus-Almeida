package br.com.db.api.controller;

import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.model.Pessoa;
import br.com.db.api.service.impl.PessoaServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaServiceImpl pessoaService;
    @Autowired
    public PessoaController(PessoaServiceImpl pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Pessoa> salvar(@RequestBody @Valid CadastroPessoa pessoa){
        return new ResponseEntity<>(pessoaService.salvar(pessoa), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ListagemPessoas>> listarPessoas(){
       return new ResponseEntity<>(pessoaService.listarPessoas(), HttpStatus.OK);
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<ListagemPessoas> listarPorId(@PathVariable Long id){
        return new ResponseEntity<>(pessoaService.listarPessoaPorId(id), HttpStatus.OK);
    }
    @GetMapping("/listarPessoas")
    public ResponseEntity<List<ListagemPessoas>> listarPorCep(@RequestParam String cep){
        return new ResponseEntity<>(pessoaService.listarPorCep(cep), HttpStatus.OK);
    }
    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid AtualizarPessoas pessoas){
        pessoaService.atualizar(pessoas);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        pessoaService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
