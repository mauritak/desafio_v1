package br.com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.persistence.entities.RegiaoEntity;
import br.com.app.service.AgenteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/regiao")
public class RegiaoController {

	@Autowired
	private AgenteService service;

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<RegiaoEntity>> pesquisarAgentesPorRegiao(@RequestParam("sigla") String sigla) {
		log.info("### pesquisando regiao {}", sigla);
		
		List<RegiaoEntity> response = service.pesquisarAgentesPorRegiao(sigla);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

}
