package com.ejercicioREST.Ejercicio.Rest;

import com.ejercicioREST.Ejercicio.Rest.entities.Laptop;
import com.ejercicioREST.Ejercicio.Rest.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class EjercicioRestApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(EjercicioRestApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null, "Lenovo Ideapad", 6890.99, LocalDate.of(2019, 11, 22), 28);
		Laptop laptop2 = new Laptop(null, "Hp Pavilion", 6890.99, LocalDate.of(2020, 8, 11), 34);
		Laptop laptop3 = new Laptop(null, "Dell Latitude 7490", 6890.99, LocalDate.of(2022, 6, 9), 17);

		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);

		System.out.println(repository.findAll().size());

	}

}
