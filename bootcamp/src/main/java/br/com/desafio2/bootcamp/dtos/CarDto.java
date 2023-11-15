package br.com.desafio2.bootcamp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
    private Integer carID;
    private String model;
    private String brand;
    private String color;
    private Integer year;

    private String plate;

    private Boolean available;
}
