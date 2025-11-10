package org.example.transferbanc.service;

import org.example.transferbanc.model.Account;
import org.example.transferbanc.model.Client;
import org.example.transferbanc.model.Transfer;
import org.example.transferbanc.repository.AccountRepository;
import org.example.transferbanc.repository.ClientRepository;
import org.example.transferbanc.repository.TransferRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Service
public class BankService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public BankService(ClientRepository clientRepository,
                       AccountRepository accountRepository,
                       TransferRepository transferRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    // Crear cliente
    public Mono<Client> createClient(Client client) {
        return clientRepository.save(client);
    }

    // Crear cuenta
    public Mono<Account> createAccount(Account account) {
        return accountRepository.save(account);
    }

    // Consultar cuenta por número
    public Mono<Account> getAccountByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    // Listar transferencias
    public Flux<Transfer> listTransfers() {
        return transferRepository.findAll();
    }

    // Depósito
    public Mono<Account> deposit(String number, BigDecimal amount) {
        return accountRepository.findByNumber(number)
                .flatMap(account -> {
                    account.setBalance(account.getBalance().add(amount));
                    return accountRepository.save(account);
                });
    }

    // Transferencia
    public Mono<Transfer> transfer(String from, String to, BigDecimal amount) {
        return accountRepository.findByNumber(from)
                .zipWith(accountRepository.findByNumber(to))
                .flatMap(tuple -> {
                    Account fromAccount = tuple.getT1();
                    Account toAccount = tuple.getT2();

                    if (fromAccount.getBalance().compareTo(amount) < 0) {
                        return Mono.error(new IllegalArgumentException("Fondos insuficientes"));
                    }

                    fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
                    toAccount.setBalance(toAccount.getBalance().add(amount));

                    return accountRepository.save(fromAccount)
                            .then(accountRepository.save(toAccount))
                            .then(transferRepository.save(
                                    new Transfer(null, fromAccount.getId(), toAccount.getId(), amount, "COMPLETADA", null)
                            ));
                });
    }
}
