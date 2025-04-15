package com.mercado.mercado_medieval.controller;

import com.mercado.mercado_medieval.model.Personagem;
import com.mercado.mercado_medieval.repository.PersonagemRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    private final PersonagemRepository repository;

    public PersonagemController(PersonagemRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todos os personagens.
     */
    @GetMapping
    public List<Personagem> listarTodos() {
        return repository.findAll();
    }

    /**
     * Busca um personagem pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca personagens que contenham parte do nome fornecido (case-insensitive).
     */
    @GetMapping("/buscar")
    public List<Personagem> buscarPorNome(@RequestParam String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    /**
     * Busca personagens pela classe.
     */
    @GetMapping("/classe/{classe}")
    public List<Personagem> buscarPorClasse(@PathVariable String classe) {
        return repository.findByClasse(classe);
    }

    /**
     * Cria um novo personagem.
     */
    @PostMapping
    public Personagem criar(@Valid @RequestBody Personagem personagem) {
        return repository.save(personagem);
    }

    /**
     * Atualiza um personagem existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Personagem> atualizar(@PathVariable Long id, @Valid @RequestBody Personagem novoPersonagem) {
        return repository.findById(id)
                .map(personagem -> {
                    personagem.setNome(novoPersonagem.getNome());
                    personagem.setClasse(novoPersonagem.getClasse());
                    personagem.setNivel(novoPersonagem.getNivel());
                    personagem.setMoedas(novoPersonagem.getMoedas());
                    return ResponseEntity.ok(repository.save(personagem));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deleta um personagem pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(personagem -> {
                    repository.delete(personagem);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
