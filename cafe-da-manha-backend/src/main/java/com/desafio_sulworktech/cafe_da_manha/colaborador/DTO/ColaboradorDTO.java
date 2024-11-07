package com.desafio_sulworktech.cafe_da_manha.colaborador.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ColaboradorDTO {

	private UUID id;
	private String nome;
	private String cpf;
	private LocalDateTime dataCafe;
	private List<String> opcoesCafe;
	private boolean trouxeOpcao;

}
