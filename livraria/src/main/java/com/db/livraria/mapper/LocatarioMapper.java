package com.db.livraria.mapper;

import com.db.livraria.dto.AtualizarLocatario;
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
    static Locatario toLocatario(Locatario locatario,AtualizarLocatario locatarioAtualizado){
        locatario.setNome(locatarioAtualizado.getNome());
        locatario.setGenero(locatarioAtualizado.getGenero());
        locatario.setTelefone(locatarioAtualizado.getTelefone());
        locatario.setEmail(locatarioAtualizado.getEmail());
        locatario.setDataNascimento(locatarioAtualizado.getDataNascimento());
        return locatario;
    }
}
