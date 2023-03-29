package com.db.livraria.dto;

import com.db.livraria.model.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.ISBN;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CadastroLivro {
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;
    @ISBN(message = "ISBN invalido")
    private String isbn;
    private LocalDate dataPublicacao;
    private List<Autor> autores;
}
