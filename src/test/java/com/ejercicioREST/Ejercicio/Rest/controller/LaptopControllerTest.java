package com.ejercicioREST.Ejercicio.Rest.controller;

import com.ejercicioREST.Ejercicio.Rest.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }


    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptop", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void findOneId() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptop/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = """
                {
                     "model": "ASUS 3G Nova",
                     "price": 7890.89,
                     "releaseDate": "2021-10-03",
                     "stock": 19
                 }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals("ASUS 3G Nova", result.getModel());
    }
    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = """
                {
                    "id": 1,
                    "model": "ASUS 3G Nova",
                    "price": 7890.89,
                    "releaseDate": "2021-10-03",
                    "stock": 19
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop", HttpMethod.PUT, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void delete() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Laptop> request = new HttpEntity<>(headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop/1", HttpMethod.DELETE, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Laptop> request = new HttpEntity<>(headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop", HttpMethod.DELETE, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}