package com.example.Ejercicios7_8_9.Controladores;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${app.nombre}")
    String nombre;
    @Value("${app.mensaje}")
    String mensaje;

    @GetMapping("/saludar")
    public String saludo() {
        return "Hola, soy "+nombre+" ,desde "+mensaje ;
    }
}
