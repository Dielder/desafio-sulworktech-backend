package com.desafio_sulworktech.cafe_da_manha.colaborador.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio_sulworktech.cafe_da_manha.colaborador.entity.Colaborador;
import com.desafio_sulworktech.cafe_da_manha.colaborador.service.ColaboradorService;

@RestController
@RequestMapping("/api/cafe-da-manha")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;

	// Listar todos os colaboradores
	@GetMapping
	public ResponseEntity<List<Colaborador>> listarColaboradores() {
		List<Colaborador> colaboradores = colaboradorService.listarColaboradores();
		return ResponseEntity.ok(colaboradores);
	}

	// Obter colaborador por ID
	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> getColaboradorById(@PathVariable UUID id) {
		Optional<Colaborador> optionalColaborador = colaboradorService.getColaboradorById(id);

		return optionalColaborador.map(colaborador -> new ResponseEntity<>(colaborador, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Salvar um novo colaborador
	@PostMapping
	public ResponseEntity<?> salvarColaborador(@RequestBody Colaborador colaborador) {
		try {
			Colaborador novoColaborador = colaboradorService.salvarColaborador(colaborador);
			return ResponseEntity.status(HttpStatus.CREATED).body(novoColaborador);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Atualizar colaborador existente
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarColaborador(@PathVariable UUID id,
			@RequestBody Colaborador colaboradorAtualizado) {
		try {
			Colaborador colaboradorAtualizadoResult = colaboradorService.atualizarColaborador(id,
					colaboradorAtualizado);
			return ResponseEntity.ok(colaboradorAtualizadoResult);
		} catch (Exception e) {
			if (e.getMessage().equals("Colaborador não encontrado para atualização.")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
	}

	// Deletar colaborador pelo ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarColaborador(@PathVariable UUID id) {
		colaboradorService.deletarColaborador(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Colaborador deletado com sucesso.");
	}
}
