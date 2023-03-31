package com.db.livraria.mapper;

import com.db.livraria.model.Aluguel;
import com.db.livraria.model.Livro;
import com.db.livraria.model.Locatario;

import java.time.LocalDateTime;
import java.util.List;

public interface AluguelMapper {

    static Aluguel toAluguel(List<Livro> livrosAchados, Locatario locatarioEncontrado){
        return Aluguel.builder()
                .dataRetirada(LocalDateTime.now())
                .dataDevolucao(LocalDateTime.now().plusDays(2).withHour(22).withMinute(0))
                .livros(livrosAchados)
                .locatario(locatarioEncontrado)
                .build();
    }

}
