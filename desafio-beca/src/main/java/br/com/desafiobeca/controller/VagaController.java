package br.com.desafiobeca.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiobeca.model.Vaga;
import br.com.desafiobeca.service.impl.VagaServiceImpl;

@RestController
@RequestMapping("vaga")
public class VagaController {

	@Autowired
	VagaServiceImpl vagaService;

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ResponseEntity<Vaga> salvar(@RequestBody @Valid Integer numeroVaga) {
		return ResponseEntity.status(HttpStatus.CREATED).body(vagaService.salvar(numeroVaga));
	}

	@RequestMapping(value = "atualizar", method = RequestMethod.PUT)
	public ResponseEntity<Vaga> atualizar(@RequestBody @Valid Vaga vaga) {
		return ResponseEntity.status(HttpStatus.OK).body(vagaService.atualizar(vaga));
	}

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public ResponseEntity<List<Vaga>> listarTodasVagas() {
		return ResponseEntity.status(HttpStatus.OK).body(vagaService.listarTodasVagas());
	}

	@RequestMapping(value = "listar/id={id}", method = RequestMethod.GET)
	public ResponseEntity<Vaga> listarVagaPorId(@RequestBody @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(vagaService.listarPorId(id).get());
	}

	@RequestMapping(value = "listar/numeroVaga={numeroVaga}", method = RequestMethod.GET)
	public ResponseEntity<Vaga> listarVagaPorNumeroVaga(@RequestBody @PathVariable Integer numeroVaga) {
		return ResponseEntity.status(HttpStatus.OK).body(vagaService.listarPorNumeroVaga(numeroVaga));
	}

	@RequestMapping(value = "listar/ocupado={ocupado}", method = RequestMethod.GET)
	public ResponseEntity<List<Vaga>> listarPorOcupacao(@RequestBody @PathVariable boolean ocupado) {
		return ResponseEntity.status(HttpStatus.OK).body(vagaService.listarPorOcupacao(ocupado));
	}
}
