package br.com.desafio2.bootcamp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
public class StatusController {
    @GetMapping
    public String getStatus() {return "Server in running"; }
}
