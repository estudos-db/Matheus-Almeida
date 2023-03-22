package br.com.db.api.controller;

import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.dto.ListagemPessoas;
import br.com.db.api.exception.NotFoundException;
import br.com.db.api.model.Pessoa;
import br.com.db.api.service.impl.PessoaServiceImpl;
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

    @PostMapping()
    public ResponseEntity<Pessoa> salvar(@RequestBody @Valid CadastroPessoa pessoa){
        return new ResponseEntity<>(pessoaService.salvar(pessoa), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ListagemPessoas>> buscarPessoas(@RequestParam(required = false) String cep){
        if (cep != null){
            return new ResponseEntity<>(pessoaService.buscarPessoasPorCep(cep), HttpStatus.OK);
        }
       return new ResponseEntity<>(pessoaService.buscarPessoas(), HttpStatus.OK);
    }
    @GetMapping("{idPessoa}")
    public ResponseEntity<ListagemPessoas> buscarPorId(@PathVariable Long idPessoa){
        return new ResponseEntity<>(pessoaService.buscarPessoaPorId(idPessoa), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ListagemPessoas> atualizar(@RequestBody @Valid AtualizarPessoas pessoas){
        return new ResponseEntity<>(pessoaService.atualizar(pessoas), HttpStatus.OK);
    }

    @DeleteMapping("{idPessoa}")
    public ResponseEntity<?> deletar(@PathVariable Long idPessoa){
        pessoaService.deletar(idPessoa);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
