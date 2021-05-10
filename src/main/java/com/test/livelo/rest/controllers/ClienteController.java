package com.test.livelo.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.livelo.persistence.dto.cliente.CadastrarClienteDTO;
import com.test.livelo.persistence.dto.cliente.EditarClienteDTO;
import com.test.livelo.persistence.model.Cliente;
import com.test.livelo.service.ClienteService;
import com.test.livelo.service.exception.NegocioException;
import com.test.livelo.service.exception.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Endpoints de Cliente" })
@RequestMapping(value = "cliente")
public class ClienteController {

	private Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService clienteService;

	@GetMapping("{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		log.debug("Requisição REST para buscar o cliente : {}", id);
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		log.debug("Requisição REST para remover o Cliente : {}", id);
		clienteService.remover(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<?>> buscarPorNome(@PathVariable String nome) {
		log.debug("Requisição REST para buscar a Cliente por nome : {}", nome);
		return ResponseEntity.ok(clienteService.buscarPorNome(nome));
	}

	@PostMapping
	@ApiOperation(value = "Cadastra um cliente na aplicação")
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarClienteDTO cadastrar) {
		log.debug("Requisição REST para cadastrar um cliente : {}", cadastrar);
		clienteService.cadastrar(cadastrar);
		return ResponseEntity.ok().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody EditarClienteDTO editar) {
		log.debug("Requisição REST para editar o Cliente : {}", editar);
		clienteService.atualizar(id, editar);
		return ResponseEntity.ok().build();
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<JsonNode> handleException(NegocioException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ObjectNode jsonNode = new ObjectMapper().createObjectNode();
		jsonNode.put("status", badRequest.value());
		jsonNode.put("message", e.getMessage());
		return ResponseEntity.status(badRequest).body(jsonNode);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<JsonNode> handleException(NotFoundException e) {
		HttpStatus badRequest = HttpStatus.NOT_FOUND;
		ObjectNode jsonNode = new ObjectMapper().createObjectNode();
		jsonNode.put("status", badRequest.value());
		jsonNode.put("message", e.getMessage());
		return ResponseEntity.status(badRequest).body(jsonNode);
	}

}
