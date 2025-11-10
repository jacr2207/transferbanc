package org.example.transferbanc.repository;

import org.example.transferbanc.model.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {
}
