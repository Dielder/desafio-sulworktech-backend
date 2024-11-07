package com.desafio_sulworktech.cafe_da_manha.colaborador.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio_sulworktech.cafe_da_manha.colaborador.entity.Colaborador;

import jakarta.transaction.Transactional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, UUID> {

	@Query(value = "SELECT * FROM colaborador WHERE data_cafe = :dataCafe", nativeQuery = true)
	List<Colaborador> findAllByDataCafe(LocalDate dataCafe);

	@Modifying
	@Transactional
	@Query(value = "UPDATE colaborador SET trouxe_opcao = :trouxeOpcao WHERE id = :id", nativeQuery = true)
	void atualizarTrouxeOpcao(@Param("id") UUID id, @Param("trouxeOpcao") boolean trouxeOpcao);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO colaborador (id, nome, cpf, data_cafe, opcoes_cafe) VALUES (:id, :nome, :cpf, :dataCafe, :opcoesCafe)", nativeQuery = true)
	void insertColaborador(@Param("id") UUID id, @Param("nome") String nome, @Param("cpf") String cpf,
			@Param("dataCafe") LocalDateTime dataCafe, @Param("opcoesCafe") String opcoesCafe);

	@Modifying
	@Transactional
	@Query(value = "UPDATE colaborador SET nome = :nome, cpf = :cpf, data_cafe = :dataCafe, opcoes_cafe = :opcoesCafe WHERE id = :id", nativeQuery = true)
	void updateColaborador(@Param("id") UUID id, @Param("nome") String nome, @Param("cpf") String cpf,
			@Param("dataCafe") LocalDateTime dataCafe, @Param("opcoesCafe") String opcoesCafe);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM colaborador WHERE id = :id", nativeQuery = true)
	void deleteColaborador(@Param("id") UUID id);

	boolean existsByCpf(String cpf);
}
