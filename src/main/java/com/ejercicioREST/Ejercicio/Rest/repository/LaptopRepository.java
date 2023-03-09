package com.ejercicioREST.Ejercicio.Rest.repository;

import com.ejercicioREST.Ejercicio.Rest.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
