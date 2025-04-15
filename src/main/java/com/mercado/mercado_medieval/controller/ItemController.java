package com.mercado.mercado_medieval.controller;

import com.mercado.mercado_medieval.model.Item;
import com.mercado.mercado_medieval.repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todos os itens cadastrados.
     */
    @GetMapping
    public List<Item> listarTodos() {
        return repository.findAll();
    }

    /**
     * Busca um item pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca itens que contenham parte do nome (case-insensitive).
     */
    @GetMapping("/buscar")
    public List<Item> buscarPorNome(@RequestParam String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    /**
     * Busca itens por tipo.
     */
    @GetMapping("/tipo/{tipo}")
    public List<Item> buscarPorTipo(@PathVariable String tipo) {
        return repository.findByTipo(tipo);
    }

    /**
     * Busca itens por raridade.
     */
    @GetMapping("/raridade/{raridade}")
    public List<Item> buscarPorRaridade(@PathVariable String raridade) {
        return repository.findByRaridade(raridade);
    }

    /**
     * Busca itens dentro de uma faixa de preço.
     */
    @GetMapping("/preco")
    public List<Item> buscarPorFaixaDePreco(@RequestParam double min, @RequestParam double max) {
        return repository.findByPrecoBetween(min, max);
    }

    /**
     * Cria um novo item.
     */
    @PostMapping
    public Item criar(@Valid @RequestBody Item item) {
        return repository.save(item);
    }

    /**
     * Atualiza um item existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizar(@PathVariable Long id, @Valid @RequestBody Item novoItem) {
        return repository.findById(id)
                .map(item -> {
                    item.setNome(novoItem.getNome());
                    item.setTipo(novoItem.getTipo());
                    item.setRaridade(novoItem.getRaridade());
                    item.setPreco(novoItem.getPreco());
                    item.setDono(novoItem.getDono());
                    return ResponseEntity.ok(repository.save(item));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deleta um item pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(item -> {
                    repository.delete(item);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
