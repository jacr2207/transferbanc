package org.example.transferbanc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("transfer")
public class Transfer {
    @Id
    private Long id;
    private Long fromAccount;
    private Long toAccount;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
}
