package org.example.transferbanc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@Data
@Table("account")
public class Account {
    @Id
    private Long id;
    private Long clientId;
    private String number;
    private BigDecimal balance;
}
