package br.com.db.api.repository;

import br.com.db.api.pessoa.ListagemPessoas;
import br.com.db.api.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<ListagemPessoas> findByEnderecoCep(String cep);
}
