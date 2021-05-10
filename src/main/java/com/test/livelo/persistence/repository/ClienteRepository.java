package com.test.livelo.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.livelo.persistence.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	

	List<Cliente> findByNome(String nome);
	
}
