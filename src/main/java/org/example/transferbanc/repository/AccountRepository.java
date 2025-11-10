package org.example.transferbanc.repository;

import org.example.transferbanc.model.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AccountRepository extends ReactiveCrudRepository<Account, Long> {
    Mono<Account> findByNumber(String number);
}
