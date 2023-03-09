package com.ejercicioREST.Ejercicio.Rest.controller;

import com.ejercicioREST.Ejercicio.Rest.entities.Laptop;
import com.ejercicioREST.Ejercicio.Rest.repository.LaptopRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private LaptopRepository laptopRepository;

    private LaptopController(LaptopRepository laptopRepository){
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptop")
    public List<Laptop> findAll(){
        //return laptopRepository.findAll();
        System.out.println("Entro a este proceso");
        return laptopRepository.findAll();
    }
    /*
    @GetMapping("/saludoprueba")
    public String prueba(){
        return "Prueba de texto";
    }
    */

    @GetMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> findOneId(@PathVariable Long id){
        Optional<Laptop> laptop = laptopRepository.findById(id);
        if(laptop.isPresent())
            return ResponseEntity.ok(laptop.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/laptop")
    public Laptop create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        return laptopRepository.save(laptop);
    }

    @PutMapping("/api/laptop")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())){
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptop")
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
