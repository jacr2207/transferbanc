package org.example.transferbanc.controller;

import org.example.transferbanc.model.Account;
import org.example.transferbanc.model.Client;
import org.example.transferbanc.model.Transfer;
import org.example.transferbanc.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class BankController {

    private final BankService svc;

    public BankController(BankService svc) { this.svc = svc; }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Client> createClient(@RequestBody Client client) {
        return svc.createClient(client);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> createAccount(@RequestBody Account account) {
        return svc.createAccount(account);
    }

    @PostMapping("/accounts/{number}/deposit")
    public Mono<Account> deposit(@PathVariable String number, @RequestParam BigDecimal amount) {
        return svc.deposit(number, amount);
    }

    @PostMapping("/transfer")
    public Mono<Transfer> transfer(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount) {
        return svc.transfer(from, to, amount);
    }

    @GetMapping("/accounts/{number}")
    public Mono<Account> getAccount(@PathVariable String number) {
        return svc.getAccountByNumber(number);
    }

    @GetMapping("/transfers")
    public Flux<Transfer> listTransfers() {
        return svc.listTransfers();
    }
}
