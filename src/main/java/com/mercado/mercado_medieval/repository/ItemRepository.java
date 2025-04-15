package com.mercado.mercado_medieval.repository;

import com.mercado.mercado_medieval.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNomeContainingIgnoreCase(String nome);
    List<Item> findByTipo(String tipo);
    List<Item> findByPrecoBetween(double min, double max);
    List<Item> findByRaridade(String raridade);
}
