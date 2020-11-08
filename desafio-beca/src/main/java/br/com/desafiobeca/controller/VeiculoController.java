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

import br.com.desafiobeca.model.Veiculo;
import br.com.desafiobeca.service.impl.VeiculoServiceImpl;

@RestController
@RequestMapping("veiculo")
public class VeiculoController {

	@Autowired
	VeiculoServiceImpl veiculoService;

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ResponseEntity<Veiculo> salvar(@RequestBody @Valid Veiculo veiculo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.salvar(veiculo));
	}

	@RequestMapping(value = "atualizar", method = RequestMethod.PUT)
	public ResponseEntity<Veiculo> atualizar(@RequestBody @Valid Veiculo veiculo) {
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.atualizar(veiculo));
	}

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listarTodosVeiculos() {
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.listarTodosVeiculos());
	}

	@RequestMapping(value = "listar/id={id}", method = RequestMethod.GET)
	public ResponseEntity<Veiculo> listarVeiculoPorId(@RequestBody @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.listarPorId(id));
	}

	@RequestMapping(value = "listar/placa={placa}", method = RequestMethod.GET)
	public ResponseEntity<Veiculo> listarVeiculoPorPlaca(@RequestBody @PathVariable String placa) {
		return ResponseEntity.status(HttpStatus.OK).body(veiculoService.listarVeiculoPorPlaca(placa));
	}
}
