package com.db.livraria.controller;

import com.db.livraria.dto.request.CadastroAutor;
import com.db.livraria.model.Autor;
import com.db.livraria.repository.AutorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.Year;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AutorControllerTest {
    private static final String URL = "/autor";
    @LocalServerPort
    private int port;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Autor autor;

    @BeforeEach
    void init(){
            autor = Autor.builder()
                    .id(1L)
                    .nome("Joao")
                    .genero("Masculino")
                    .anoNascimento(Year.of(2010))
                    .cpf("53494105049")
                    .build();

            RestAssured.port = port;

    }

    @Test
    void deveCadastrarAutor() throws Exception {
        String cadastroAutorAsJson = objectMapper.writeValueAsString(
                CadastroAutor.builder()
                .nome("Joao")
                .genero("Masculino")
                .anoNascimento(Year.of(2010))
                .cpf("03234536007")
                .build());

        given()
                .contentType(ContentType.JSON)
                .body(cadastroAutorAsJson)
                .when()
                .post(URL)
                .then()
                .statusCode(201);
    }
    @Test
    void deveRetornarAutorAoBuscarPeloNomeNoParametro(){
        autorRepository.save(autor);
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL + "?nome=Joao")
                .then()
                .statusCode(200);
    }
    @Test
    void deveDeletarUmAutor(){
        autorRepository.save(autor);
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/1")
                .then()
                .statusCode(200);
    }



}
