package br.com.desafio2.bootcamp.dtos;

import java.sql.Date;

public class ReservationDto {
    private Integer reservationID;
    private Date startDate;

    private Date endDate;

    private CarDto cars;

    private  ClientDto clients;
}
