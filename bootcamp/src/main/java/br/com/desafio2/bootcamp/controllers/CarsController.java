package br.com.desafio2.bootcamp.controllers;

import br.com.desafio2.bootcamp.dtos.CarDto;
import br.com.desafio2.bootcamp.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
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

    @PostMapping
    public ResponseEntity<CarDto> saveCar(@RequestBody CarDto carDto) {
        CarDto savedCar = carsService.saveCar(carDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarDto> editCar(@PathVariable Integer carId, @RequestBody CarDto carDto) {
        CarDto updatedCar = carsService.updateCar(carId,carDto);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{carID}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer carId) {
        carsService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }
}
