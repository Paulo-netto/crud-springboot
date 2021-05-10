package com.test.livelo.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.livelo.persistence.dto.cidade.CidadeDTO;
import com.test.livelo.persistence.model.Cidade;

@Repository
public interface CidadeRepositoy extends JpaRepository<Cidade, Long> {

	@Query(value = "SELECT new com.test.livelo.persistence.dto.cidade.CidadeDTO(c.nome,c.estado)"
			+ " FROM Cidade c WHERE LOWER(c.nome) LIKE lower(concat('%', :nome,'%'))")
	List<CidadeDTO> findByNome(@Param("nome") String nome);

	@Query(value = "SELECT new com.test.livelo.persistence.dto.cidade.CidadeDTO(c.nome,c.estado)"
			+ " FROM Cidade c WHERE LOWER(c.estado) LIKE lower(concat('%', :estado,'%'))")
	List<CidadeDTO> findByEstado(String estado);

}
