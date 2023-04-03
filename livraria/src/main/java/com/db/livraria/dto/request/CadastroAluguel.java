package com.db.livraria.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class CadastroAluguel {
    private List<Long> idLivros;
    private Long idLocatario;
}
