package com.db.livraria.controller;

import com.db.livraria.dto.CadastroLivro;
import com.db.livraria.model.Livro;
import com.db.livraria.service.impl.LivroServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {
    //TODO:Mudar o método de retorno do CRUD(DTO)

    private final LivroServiceImpl livroService;
    @Autowired
    public LivroController(LivroServiceImpl livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> salvarLivro(@RequestBody @Valid CadastroLivro cadastroLivro){
        return new ResponseEntity<>(livroService.salvar(cadastroLivro), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> buscarLivroDisponiveisParaAlugar(){
        return new ResponseEntity<>(livroService.buscarLivrosDisponiveisParaAlugar(), HttpStatus.OK);
    }
    @GetMapping("/alugado")
    public ResponseEntity<List<Livro>> buscarLivrosAlugados(){
        return new ResponseEntity<>(livroService.buscarLivrosAlugados(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id){
        return new ResponseEntity<>(livroService.buscarLivroPorId(id), HttpStatus.OK);
    }
    //TODO:Criar um controller pra buscar livros alugados por um locatario

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarLivroPorId(@PathVariable Long id){
        livroService.deletarLivroPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
