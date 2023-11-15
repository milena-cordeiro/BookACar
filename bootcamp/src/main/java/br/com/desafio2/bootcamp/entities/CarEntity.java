package br.com.desafio2.bootcamp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carID")
    private Integer carID;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "year")
    private Integer year;

    @Column(name = "plate")
    private String plate;

    @Column(name = "available")
    private Boolean available;

    @OneToMany(mappedBy = "carId")
    private List<ReservationEntity> reservations;
}
