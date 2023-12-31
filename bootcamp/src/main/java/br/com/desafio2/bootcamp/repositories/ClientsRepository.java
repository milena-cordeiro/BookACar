package br.com.desafio2.bootcamp.repositories;

import br.com.desafio2.bootcamp.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<ClientEntity, Integer> {
}
