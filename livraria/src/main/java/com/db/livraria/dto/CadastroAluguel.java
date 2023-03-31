package com.db.livraria.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class CadastroAluguel {
    private List<Long> idLivros;
    private Long idLocatario;
}
