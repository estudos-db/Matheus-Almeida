package com.db.livraria.controller;

import com.db.livraria.dto.request.AtualizarLocatario;
import com.db.livraria.dto.request.CadastroLocatario;
import com.db.livraria.model.Locatario;
import com.db.livraria.repository.LocatarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
 class LocatarioControllerTest {
    private static final String URL = "/locatario";
    @LocalServerPort
    private int port;
    @Autowired
    private LocatarioRepository locatarioRepository;
    @Autowired
    private final ObjectMapper mapper = new ObjectMapper();
    private Locatario locatario;

    @BeforeEach
    void init(){
        RestAssured.port = port;
        locatario = Locatario.builder()
                .id(1L)
                .nome("Matheus")
                .genero("Masculino")
                .telefone("1122223333")
                .email("teste@email.com")
                .dataNascimento(LocalDate.of(2022,11,15))
                .cpf("61077855079")
                .build();

    }

    @Test
    void deveSalvarLocatario() throws JsonProcessingException {
        String locatarioAsJson = mapper.writeValueAsString(CadastroLocatario.builder()
                .nome("Matheus")
                .genero("Masculino")
                .telefone("1122223333")
                .email("teste2@email.com")
                .dataNascimento(LocalDate.of(2022,11,15))
                .cpf("69157490040").build());

        given()
                .contentType(ContentType.JSON)
                .body(locatarioAsJson)
                .when()
                .post(URL)
                .then()
                .statusCode(201);
    }
    @Test
    void deveRetornarLocatarioPorId() {
        locatarioRepository.save(locatario);
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL + "/1")
                .then()
                .statusCode(200);
    }
    @Test
    void deveRetornarLocatarioPorNome() {
        locatarioRepository.save(locatario);
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL + "?nome=Matheus")
                .then()
                .statusCode(200);
    }
    @Test
    void deveAtualizarLocatario() throws JsonProcessingException {
        String locatarioAtualizadoAsJson = mapper.writeValueAsString(AtualizarLocatario.builder()
                .nome("Matheus")
                .genero("Masculino")
                .telefone("2233334444")
                .email("teste@email.com")
                .dataNascimento(LocalDate.of(2022,11,15))
                .build());
        locatarioRepository.save(locatario);

        given()
                .body(locatarioAtualizadoAsJson)
                .contentType(ContentType.JSON)
                .when()
                .put(URL + "/2")
                .then()
                .statusCode(200);
    }
    @Test
    void deveDeletarLocatario() {
        locatarioRepository.save(locatario);
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/1")
                .then()
                .statusCode(200);
    }

}
