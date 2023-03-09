package com.ejercicioREST.Ejercicio.Rest.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private Double price;
    private LocalDate releaseDate;
    private Integer stock;

    public Laptop() {
    }

    public Laptop(Long id, String model, Double price, LocalDate releaseDate, Integer stock) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.releaseDate = releaseDate;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() { return price; }

    public void setPrice(Double price){
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
