package br.com.desafio2.bootcamp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationID")
    private Integer reservationID;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDcar")
    private CarEntity cars;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDclient")
    private ClientEntity clients;


}
