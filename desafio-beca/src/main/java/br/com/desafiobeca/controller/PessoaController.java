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

import br.com.desafiobeca.model.Pessoa;
import br.com.desafiobeca.service.impl.PessoaServiceImpl;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

	@Autowired
	PessoaServiceImpl pessoaService;

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ResponseEntity<Pessoa> salvar(@RequestBody @Valid Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.salvar(pessoa));
	}

	@RequestMapping(value = "atualizar", method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> atualizar(@RequestBody @Valid Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.atualizar(pessoa));
	}

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listarTodasPessoas() {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarTodasPessoas());
	}

	@RequestMapping(value = "listar/id={id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> listarPessoaPorId(@RequestBody @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarPorId(id).get());
	}

	@RequestMapping(value = "listar/nome={nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listarPessoaPorNome(@RequestBody @PathVariable String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarPorNome(nome));
	}

	@RequestMapping(value = "listar/cpf={cpf}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> listarPessoaPorCpf(@RequestBody @PathVariable String cpf) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarPorCpf(cpf));
	}

}
