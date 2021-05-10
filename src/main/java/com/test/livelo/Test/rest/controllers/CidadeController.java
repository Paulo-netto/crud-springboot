package com.test.livelo.Test.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.livelo.Test.persistence.dto.CidadeDTO;
import com.test.livelo.Test.persistence.modal.Cidade;
import com.test.livelo.Test.service.CidadeService;
import com.test.livelo.Test.service.exception.NegocioException;
import com.test.livelo.Test.service.exception.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Endpoints de Cidade" })
@RequestMapping(value = "cidade")
public class CidadeController {

	private Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private CidadeService cidadeService;

	@PostMapping
	@ApiOperation(value = "Cadastra uma cidade na aplicação")
	public ResponseEntity<Void> cadastrar(@RequestBody @Valid CidadeDTO cidadeDTO) {
		log.debug("Requisição REST para cadastrar a cidade : {}", cidadeDTO);
		cidadeService.cadastrar(cidadeDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/nome/{nome}")
	@ApiOperation(value = "Retornar a cidade pelo nome", response = Cidade.class)
	public ResponseEntity<List<?>> buscarPorNome(@PathVariable String nome) {
		log.debug("Requisição REST para buscar a Cidade por nome : {}", nome);
		return ResponseEntity.ok(cidadeService.buscarPorNome(nome));
	}

	@GetMapping("/estado/{estado}")
	@ApiOperation(value = "Retornar a cidade pelo estado", response = Cidade.class)
	public ResponseEntity<List<?>> buscarPorEstado(@PathVariable String estado) {
		log.debug("Requisição REST para buscar a Cidade por estado : {}", estado);
		return ResponseEntity.ok(cidadeService.buscarPorEstado(estado));
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
