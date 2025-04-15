package com.mercado.mercado_medieval.repository;

import com.mercado.mercado_medieval.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByNomeContainingIgnoreCase(String nome);
    List<Personagem> findByClasse(String classe);
}
