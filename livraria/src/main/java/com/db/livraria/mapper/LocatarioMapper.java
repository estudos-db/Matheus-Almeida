package com.db.livraria.mapper;

import com.db.livraria.dto.CadastroLocatario;
import com.db.livraria.model.Locatario;

public interface LocatarioMapper {

    static Locatario toLocatario(CadastroLocatario cadastroLocatario){
        return Locatario.builder()
                .nome(cadastroLocatario.getNome())
                .genero(cadastroLocatario.getGenero())
                .telefone(cadastroLocatario.getTelefone())
                .email(cadastroLocatario.getEmail())
                .dataNascimento(cadastroLocatario.getDataNascimento())
                .cpf(cadastroLocatario.getCpf())
                .build();
    }
}
