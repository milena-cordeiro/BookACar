package br.com.desafio2.bootcamp.controllers;

import br.com.desafio2.bootcamp.dtos.ClientDto;
import br.com.desafio2.bootcamp.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
@CrossOrigin(origins = "*")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients() {
        List<ClientDto> clients = clientsService.listClients();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/insert")
    public ResponseEntity<ClientDto> insertClient(@RequestBody ClientDto clientDto) {
        try {
            ClientDto savedClient = clientsService.saveClient(clientDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
        } catch (RuntimeException e) {
            // Tratamento de exceção
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
