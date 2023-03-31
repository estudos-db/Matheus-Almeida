package com.db.livraria.controller;

import com.db.livraria.dto.CadastroAluguel;
import com.db.livraria.model.Aluguel;
import com.db.livraria.service.impl.AluguelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {
    private final AluguelServiceImpl aluguelService;
    @Autowired
    public AluguelController(AluguelServiceImpl aluguelService) {
        this.aluguelService = aluguelService;
    }

    @PostMapping
    public ResponseEntity<Aluguel> salvarAluguel(@RequestBody @Valid CadastroAluguel cadastroAluguel){
        return new ResponseEntity<>(aluguelService.salvar(cadastroAluguel), HttpStatus.CREATED);
    }
}
