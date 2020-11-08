package br.com.desafiobeca.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiobeca.exceptions.AtualizaPessoaException;
import br.com.desafiobeca.model.Pessoa;
import br.com.desafiobeca.repository.PessoaRepository;
import br.com.desafiobeca.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Override
	public Pessoa salvar(Pessoa pessoa) {
		if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
			throw new EntityExistsException("Uma pessoa com este CPF já existe");
		} else {
			pessoaRepository.save(pessoa);
			return pessoa;
		}
	}

	@Override
	public Pessoa atualizar(Pessoa pessoa) {
		if (verificarPessoa(pessoa)) {
			return pessoaRepository.save(pessoa);
		} else {
			throw new AtualizaPessoaException("Essa pessoa não existe");
		}
	}

	@Override
	public List<Pessoa> listarTodasPessoas() {
		return pessoaRepository.findAll();
	}

	@Override
	public Optional<Pessoa> listarPorId(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			return pessoa;
		} else {
			throw new NullPointerException("Pessoa não encontrada");
		}
	}

	@Override
	public List<Pessoa> listarPorNome(String nome) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		listarTodasPessoas().forEach(item -> {
			if (item.getNome().equals(nome)) {
				pessoas.add(item);
			}
		});
		if (pessoas.isEmpty()) {
			throw new NullPointerException("Nenhum nome encontrado");
		}
		return pessoas;
	}

	@Override
	public Pessoa listarPorCpf(String cpf) {
		Pessoa pessoa = pessoaRepository.findByCpf(cpf);
		if (pessoa != null) {
			return pessoa;
		} else {
			throw new NullPointerException("Pessoa não encontrada");
		}
	}

	@Override
	public boolean verificarPessoa(Pessoa pessoa) {

		if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
			return true;
		} else {
			return false;
		}
	}
}
