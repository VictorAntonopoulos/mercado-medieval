package com.mercado.mercado_medieval.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do item é obrigatório.")
    private String nome;

    @NotBlank(message = "O tipo do item é obrigatório.")
    private String tipo; // considere trocar por Enum TipoItem futuramente

    @NotBlank(message = "A raridade do item é obrigatória.")
    private String raridade; // considere trocar por Enum Raridade futuramente

    @NotNull(message = "O preço do item é obrigatório.")
    @Min(value = 0, message = "O preço do item não pode ser negativo.")
    private Double preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personagem_id")
    private Personagem dono;
}
