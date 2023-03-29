package com.db.livraria.service;

import com.db.livraria.dto.CadastroLocatario;
import com.db.livraria.model.Locatario;

import java.util.List;

public interface LocatarioService {

    Locatario salvar(CadastroLocatario locatarioCadastrado);

    Locatario buscarLocatarioPorId(Long id);

    Locatario buscarLocatarioPeloNome(String nome);
}
