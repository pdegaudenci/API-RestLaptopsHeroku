package com.example.Ejercicios7_8_9.repositorios;

import com.example.Ejercicios7_8_9.Entidad.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
