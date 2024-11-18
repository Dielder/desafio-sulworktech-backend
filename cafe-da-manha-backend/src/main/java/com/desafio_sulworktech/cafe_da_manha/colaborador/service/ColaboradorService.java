package com.desafio_sulworktech.cafe_da_manha.colaborador.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio_sulworktech.cafe_da_manha.colaborador.entity.Colaborador;
import com.desafio_sulworktech.cafe_da_manha.colaborador.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	// Listar todos os colaboradores
	public List<Colaborador> listarColaboradores() {
		return colaboradorRepository.findAll();
	}

	// Salvar um novo colaborador
	public Colaborador salvarColaborador(Colaborador colaborador) throws Exception {
		validarColaborador(colaborador);
		return colaboradorRepository.save(colaborador);
	}

	// Obter colaborador por ID
	public Optional<Colaborador> getColaboradorById(UUID id) {
		return colaboradorRepository.findById(id).map(colaborador -> {
			colaborador.setId(id);
			return colaborador;
		});
	}

	// Atualizar um colaborador existente
	public Colaborador atualizarColaborador(UUID id, Colaborador colaboradorAtualizado) throws Exception {
		Optional<Colaborador> colaboradorExistente = colaboradorRepository.findById(id);

		if (colaboradorExistente.isPresent()) {
			Colaborador colaborador = colaboradorExistente.get();

			// Atualizar os campos necessários
			colaborador.setNome(colaboradorAtualizado.getNome());
			colaborador.setCpf(colaboradorAtualizado.getCpf());
			colaborador.setDataCafe(colaboradorAtualizado.getDataCafe());
			colaborador.setOpcoesCafe(colaboradorAtualizado.getOpcoesCafe());

			validarColaborador(colaborador);
			return colaboradorRepository.save(colaborador);
		} else {
			throw new Exception("Colaborador não encontrado para atualização.");
		}
	}

	// Validação de colaborador (CPF e data do café)
	private void validarColaborador(Colaborador colaborador) throws Exception {
		if (colaboradorRepository.existsByCpf(colaborador.getCpf())
				&& (colaborador.getId() == null || !colaboradorRepository.findById(colaborador.getId()).isPresent())) {
			throw new Exception("O CPF já está cadastrado.");
		}

		if (colaborador.getDataCafe().isBefore(LocalDate.now().plusDays(1))) {
			throw new Exception("A data do café deve ser futura.");
		}

		List<Colaborador> colaboradoresNoMesmoDia = colaboradorRepository.findAllByDataCafe(colaborador.getDataCafe());
		for (Colaborador p : colaboradoresNoMesmoDia) {
			if (!p.getId().equals(colaborador.getId())
					&& p.getOpcoesCafe().stream().anyMatch(colaborador.getOpcoesCafe()::contains)) {
				throw new Exception("Opção de café já escolhida para esta data.");
			}
		}
	}

	// Deletar colaborador pelo ID
	public void deletarColaborador(UUID id) {
		colaboradorRepository.deleteById(id);
	}
	
	// Utilizando a Native Query de deletar colaborador
//	public void deleteColaborador(UUID id) {
//		colaboradorRepository.deleteColaborador(id);
//	}
	
}
