package br.com.desafiobeca.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiobeca.model.Pessoa;
import br.com.desafiobeca.repository.PessoaRepository;
import br.com.desafiobeca.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	public Pessoa salvar(Pessoa pessoa) {
		if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
			throw new EntityExistsException("Uma pessoa com este CPF já existe");
		} else {
			pessoaRepository.save(pessoa);
			return pessoa;
		}
	}

	public Pessoa atualizar(Pessoa pessoa) {
		if (verificarPessoa(pessoa)) {
			return pessoaRepository.save(pessoa);
		} else {
			throw new IllegalArgumentException("Essa pessoa não existe");
		}
	}

	public List<Pessoa> listarTodasPessoas() {
		return pessoaRepository.findAll();
	}

	public Pessoa listarPorId(Long id) {
		Pessoa pessoa = pessoaRepository.findById(id).get();
		return pessoa;
	}

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

	public Pessoa listarPorCpf(String cpf) {
		Pessoa pessoa = pessoaRepository.findByCpf(cpf);
		if (pessoa != null) {
			return pessoa;
		} else {
			throw new NullPointerException("Pessoa não encontrada");
		}
	}

	public boolean verificarPessoa(Pessoa pessoa) {

		if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
			return true;
		} else {
			return false;
		}
	}
}
