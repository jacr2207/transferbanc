package org.example.transferbanc.controller;

import org.example.transferbanc.model.transferencia;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transferencias")
public class TransferController {

    @PostMapping
    public Mono<String> crearTransferencia(@RequestBody transferencia transferencia) {
        return Mono.just("Transferencia creada: " + transferencia);
    }

    @GetMapping("/{id}")
    public Mono<String> obtenerTransferencia(@PathVariable String id) {
        return Mono.just("Consulta de transferencia con ID: " + id);
    }
}
