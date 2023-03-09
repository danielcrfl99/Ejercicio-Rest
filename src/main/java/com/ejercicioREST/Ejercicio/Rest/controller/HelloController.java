package com.ejercicioREST.Ejercicio.Rest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.message}")
    String messageDev;

    @Value("${app.varexample}")
    String varexampleTest;

    @GetMapping("/saludo")
    public String saludo(){
        System.out.println(messageDev);
        System.out.println(varexampleTest);
        return "Hola, ¿Cómo estas?";
    }
}
