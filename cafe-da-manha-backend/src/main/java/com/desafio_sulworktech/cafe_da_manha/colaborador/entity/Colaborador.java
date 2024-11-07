package com.desafio_sulworktech.cafe_da_manha.colaborador.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@NotBlank(message = "O CPF é obrigatório")
	@Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números. EX: 99999999999")
	@Column(unique = true, nullable = false)
	private String cpf;

	@Column(nullable = false)
	private LocalDate dataCafe;

	@ElementCollection
	private List<String> opcoesCafe;

	private boolean trouxeOpcao;
}