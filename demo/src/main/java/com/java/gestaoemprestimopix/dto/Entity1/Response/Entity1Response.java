package com.java.gestaoemprestimopix.dto.Entity1.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO responsável por representar os dados de saída (Response)
 * enviados de volta ao cliente.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity1Response {

    private Long id;
    private String name;
    private Double value;
}
