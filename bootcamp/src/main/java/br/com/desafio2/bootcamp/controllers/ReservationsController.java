package br.com.desafio2.bootcamp.controllers;

import br.com.desafio2.bootcamp.dtos.ReservationDto;
import br.com.desafio2.bootcamp.services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("reservations")
@CrossOrigin(origins = "*")
public class ReservationsController {

    @Autowired
    ReservationServices reservationServices;

    @GetMapping
    public ResponseEntity<List<ReservationDto>> listReservations() {
        List<ReservationDto> reservations = reservationServices.listReservation();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> insertReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto insertReservation = reservationServices.saveReservation(reservationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertReservation);
    }

}
