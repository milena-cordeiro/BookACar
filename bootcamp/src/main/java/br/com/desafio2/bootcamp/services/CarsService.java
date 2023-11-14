package br.com.desafio2.bootcamp.services;

import br.com.desafio2.bootcamp.dtos.CarDto;
import br.com.desafio2.bootcamp.entities.CarEntity;
import br.com.desafio2.bootcamp.repositories.CarsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsService {
    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<CarDto> listCars() {
        List<CarEntity> cars = carsRepository.findAll();
        return cars.stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }

    public CarDto listCarById(Integer carId) {
        CarEntity car = carsRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found!"));

        return modelMapper.map(car, CarDto.class);

    }

    public List<CarDto> listCarByAvailable() {
        List<CarEntity> availableCars = carsRepository.findByAvailable(true);
        return availableCars.stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }

    public CarDto saveCar(CarDto carDto) {
        CarEntity car = modelMapper.map(carDto, CarEntity.class);
        car = carsRepository.save(car);
        return modelMapper.map(car, CarDto.class);
    }

    public CarDto updateCar(Integer carId, CarDto carDto) {
        CarEntity car = carsRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found!"));
        modelMapper.map(carDto, car);

        return modelMapper.map(car, CarDto.class);
    }

    public void deleteCar(Integer carID) {
        carsRepository.deleteById(carID);
    }
}
