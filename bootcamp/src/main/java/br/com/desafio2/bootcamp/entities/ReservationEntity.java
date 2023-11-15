package br.com.desafio2.bootcamp.entities;

import com.sun.jdi.IntegerValue;
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

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @ManyToOne
    @JoinColumn(name = "carID")
    private CarEntity carId;

    @ManyToOne
    @JoinColumn(name = "clientID")
    private ClientEntity clientId;


}
