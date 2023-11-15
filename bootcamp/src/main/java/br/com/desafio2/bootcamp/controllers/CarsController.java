package br.com.desafio2.bootcamp.controllers;

import br.com.desafio2.bootcamp.dtos.CarDto;
import br.com.desafio2.bootcamp.services.CarsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
@CrossOrigin(origins = "*")
public class CarsController {

    @Autowired
    private CarsService carsService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars() {
        List<CarDto> cars = carsService.listCars();

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Integer carId) {
        CarDto car = carsService.listCarById(carId);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/available")
    public ResponseEntity<List<CarDto>> getByAvailable() {
       List<CarDto> cars = carsService.listCarByAvailable();
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<CarDto> saveCar(@RequestBody CarDto carDto) {
        CarDto savedCar = carsService.saveCar(carDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable Integer carId) {
        try {
            carsService.deleteCar(carId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(409).body("Este carro está reservado, e não é possivel deleta-lo");
        }

    }
}
