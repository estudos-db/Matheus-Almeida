package com.db.livraria.service;

import com.db.livraria.dto.CadastroAluguel;
import com.db.livraria.model.Aluguel;

public interface AluguelService {
    Aluguel salvar(CadastroAluguel cadastroAluguel);
}
