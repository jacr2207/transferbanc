package org.example.transferbanc.model;

import lombok.Data;

@Data
public class transferencia {
    private String origen;
    private String destino;
    private double monto;
}
