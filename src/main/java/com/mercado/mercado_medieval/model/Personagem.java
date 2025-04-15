package com.mercado.mercado_medieval.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do personagem é obrigatório.")
    private String nome;

    @NotBlank(message = "A classe do personagem é obrigatória.")
    private String classe; // guerreiro, mago, arqueiro

    @Min(value = 1, message = "O nível mínimo é 1.")
    @Max(value = 99, message = "O nível máximo é 99.")
    private int nivel;

    @Min(value = 0, message = "O saldo de moedas não pode ser negativo.")
    private double moedas;
}
