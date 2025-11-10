package org.example.transferbanc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table("client")
public class Client {
    @Id
    private Long id;
    private String name;
    private String document;
}
