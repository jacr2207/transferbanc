package org.example.transferbanc.repository;

import org.example.transferbanc.model.Transfer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransferRepository extends ReactiveCrudRepository<Transfer, Long> {
}
