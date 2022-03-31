package com.example.Ejercicios7_8_9;

import com.example.Ejercicios7_8_9.Entidad.Laptop;
import com.example.Ejercicios7_8_9.repositorios.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Dentro de la misma app crear las clases necesarias para trabajar con "ordenadores":
 * <p>
 * Laptop (entidad)
 * <p>
 * LaptopRepository (repositorio)
 * <p>
 * LaptopController (controlador)
 * <p>
 * Desde LaptopController crear un método que devuelva una lista de objetos Laptop.
 * <p>
 * Probar que funciona desde Postman.
 * <p>
 * Los objetos Laptop se pueden insertar desde el método main de la clase principal.
 */

@SpringBootApplication
public class Ejercicios456Application {

    public static void main(String[] args) {


        ApplicationContext contexto = SpringApplication.run(Ejercicios456Application.class, args);

        LaptopRepository laptopRepository = contexto.getBean(LaptopRepository.class);

        Laptop laptop1 = new Laptop(null, "HP", "Pavilon", "4 GB");
        Laptop laptop2 = new Laptop(null, "HP", "Pavilon", "4 GB");

        laptopRepository.save(laptop1);
        laptopRepository.save(laptop2);


    }

}
