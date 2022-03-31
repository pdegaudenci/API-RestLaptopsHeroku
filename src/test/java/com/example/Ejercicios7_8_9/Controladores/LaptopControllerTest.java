package com.example.Ejercicios7_8_9.Controladores;

import com.example.Ejercicios7_8_9.Entidad.Laptop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;


    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> respuesta =
                testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(200, respuesta.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(respuesta.getBody());

        // Hay dos Laptops creadas
        assertNotEquals(null, laptops);

    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> resultado = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class); // La respuesta siempre sera del tipo especificado en el seghundo parametro
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders(); // Creo cabecera
        headers.setContentType(MediaType.APPLICATION_JSON); // tipo de contenido de cabecera
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //CREO JSON
        String json = """

                {

                             "marca": "LG",
                             "modelo": "All in One",
                            "ram": "4GB"

                    }

                """;

        //CREO ENTIDAD de la PETICION HTTP (Le paso contenido json y cabecera)
        HttpEntity<String> peticion = new HttpEntity<>(json, headers);
        //Genero respuesta (Envia peticion que devuelve una entidad d elibro)
        ResponseEntity<Laptop> respuesta = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, peticion, Laptop.class);// Ejecuta peticion que devuelva datos de tipo libro

        Laptop laptop = respuesta.getBody();

        assertEquals(1L, laptop.getId());
        assertEquals("LG", laptop.getMarca());


    }

    @Test
    void update() {

        HttpHeaders headers = new HttpHeaders(); // Creo cabecera
        headers.setContentType(MediaType.APPLICATION_JSON); // tipo de contenido de cabecera
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //CREO JSON
        String json = """


                             {
                                    "id": 1,
                                    "marca": "HP Actualizado",
                                    "modelo": "Pavilon",
                                    "ram": "4 GB"
                                }



                """;

        //CREO ENTIDAD de la PETICION HTTP (Le paso contenido json y cabecera)
        HttpEntity<String> peticion = new HttpEntity<>(json, headers);
        //Genero respuesta (Envia peticion que devuelve una entidad d elibro)
        ResponseEntity<Laptop> respuesta = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, peticion, Laptop.class);// Ejecuta peticion que devuelva datos de tipo libro

        Laptop laptop = respuesta.getBody();

        assertEquals(null, laptop);


    }

    @Test
    void delete() {
        ResponseEntity<Laptop> resultado = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());

    }

    @Test
    void deleteAll() {
        ResponseEntity<Laptop[]> respuesta =
                testRestTemplate.getForEntity("/api/laptops", Laptop[].class);


        List<Laptop> laptops = Arrays.asList(respuesta.getBody());


        assertEquals(0, laptops.size());
    }



}