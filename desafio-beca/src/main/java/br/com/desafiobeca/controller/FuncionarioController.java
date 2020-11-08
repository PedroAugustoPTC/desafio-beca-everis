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

import br.com.desafiobeca.model.Funcionario;
import br.com.desafiobeca.service.impl.FuncionarioServiceImpl;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

	@Autowired
	FuncionarioServiceImpl funcionarioService;

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ResponseEntity<Funcionario> salvar(@RequestBody @Valid Funcionario funcionario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.salvar(funcionario));
	}

	@RequestMapping(value = "atualizar", method = RequestMethod.PUT)
	public ResponseEntity<Funcionario> atualizar(@RequestBody @Valid Funcionario funcionario) {
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.atualizar(funcionario));
	}

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public ResponseEntity<List<Funcionario>> listarTodosFuncionarios() {
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.listarTodosFuncionarios());
	}

	@RequestMapping(value = "listar/id={id}", method = RequestMethod.GET)
	public ResponseEntity<Funcionario> listarFuncionarioPorId(@RequestBody @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.listarPorId(id));
	}

	@RequestMapping(value = "listar/nome={nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Funcionario>> listarFuncionarioPorNome(@RequestBody @PathVariable String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.listarPorNome(nome));
	}

	@RequestMapping(value = "listar/cpf={cpf}", method = RequestMethod.GET)
	public ResponseEntity<Funcionario> listarFuncionarioPorCpf(@RequestBody @PathVariable String cpf) {
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.listarPorCpf(cpf));
	}

}
