package com.test.livelo.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.livelo.persistence.modal.Cidade;

@Repository
public interface CidadeRepositoy extends JpaRepository<Cidade, Long> {

	List<Cidade> findByNome(String nome);

	List<Cidade> findByEstado(String estado);

}
