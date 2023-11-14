package br.com.desafio2.bootcamp.services;

import br.com.desafio2.bootcamp.dtos.ClientDto;
import br.com.desafio2.bootcamp.entities.ClientEntity;
import br.com.desafio2.bootcamp.repositories.ClientsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsService {
    @Autowired
    private ClientsRepository clientRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ClientDto> listClients() {
        List<ClientEntity> clients = clientRepository.findAll();

        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }

    public ClientDto saveClient(ClientDto clientDto) {
        ClientEntity client = modelMapper.map(clientDto, ClientEntity.class);
        client = clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);

    }
}
