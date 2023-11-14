package br.com.desafio2.bootcamp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private Integer clientID;
    private String name;

    private String email;
}
