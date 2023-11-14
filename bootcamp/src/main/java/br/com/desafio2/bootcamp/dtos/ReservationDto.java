package br.com.desafio2.bootcamp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class ReservationDto {
    private Integer reservationID;
    private Date startDate;

    private Date endDate;

    private CarDto cars;

    private  ClientDto clients;
}
