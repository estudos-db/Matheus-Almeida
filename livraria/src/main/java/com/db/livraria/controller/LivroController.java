package com.db.livraria.controller;

import com.db.livraria.dto.CadastroLivro;
import com.db.livraria.model.Livro;
import com.db.livraria.service.impl.LivroServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private final LivroServiceImpl livroService;
    @Autowired
    public LivroController(LivroServiceImpl livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> salvarLivro(@RequestBody @Valid CadastroLivro cadastroLivro){
        return new ResponseEntity<>(livroService.salvar(cadastroLivro), HttpStatus.CREATED);
    }
}
