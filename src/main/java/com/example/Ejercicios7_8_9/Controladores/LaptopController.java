package com.example.Ejercicios7_8_9.Controladores;

import com.example.Ejercicios7_8_9.Entidad.Laptop;
import com.example.Ejercicios7_8_9.repositorios.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
Implementar los métodos CRUD en el API REST de Laptop creada en ejercicios anteriores.

Los métodos CRUD:

findAll()

findOneById()

create()

update()

delete()

deleteAll()
 */
@RestController
public class LaptopController {

    LaptopRepository repository;

    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll() {
        return repository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar una Laptop por clave primaria id Long")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {
        Optional<Laptop> laptop = repository.findById(id);
        if (laptop.isPresent()) {
            return ResponseEntity.ok(laptop.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        if (laptop.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Laptop laptopCreada = repository.save(laptop);
        return ResponseEntity.ok(laptopCreada);


    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if (laptop.getId() == null) {
            return ResponseEntity.badRequest().build();
        } else if (!repository.existsById(laptop.getId())) {
            return ResponseEntity.notFound().build();
        } else {
            Laptop laptopActualizada = repository.save(laptop);
            return ResponseEntity.ok(laptopActualizada);
        }
    }

    @DeleteMapping("/api/Laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {
        if (repository.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Laptop> deleteAll(){
        if(repository.count()>0){
            repository.deleteAll();
        }
        return ResponseEntity.noContent().build();
    }
}