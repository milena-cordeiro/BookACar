package br.com.desafio2.bootcamp.services;

import br.com.desafio2.bootcamp.dtos.ReservationDto;
import br.com.desafio2.bootcamp.entities.CarEntity;
import br.com.desafio2.bootcamp.entities.ReservationEntity;
import br.com.desafio2.bootcamp.repositories.CarsRepository;
import br.com.desafio2.bootcamp.repositories.ClientsRepository;
import br.com.desafio2.bootcamp.repositories.ReservationsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServices {

    @Autowired
    private ReservationsRepository reservationRepository;
    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ReservationDto> listReservation() {
        List<ReservationEntity> reservations = reservationRepository.findAll();

        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }

    public ReservationDto saveReservation(ReservationDto reservationDto) {
        Integer carId = reservationDto.getCars().getCarID();
        Integer clientId = reservationDto.getClients().getClientID();

        if (!carsRepository.existsById(carId) || !clientsRepository.existsById(clientId)) {
            throw new EntityNotFoundException("Carro ou cliente não encontrado ao salvar a reserva");
        }

        ReservationEntity reservation = modelMapper.map(reservationDto, ReservationEntity.class);

        CarEntity car = carsRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        car.setAvailable(false);

        if (!car.getAvailable()) {
            throw new EntityNotFoundException("Car is not available for reservation");
        }

        reservation = reservationRepository.save(reservation);
        carsRepository.save(car);

        return modelMapper.map(reservation, ReservationDto.class);
    }
}
