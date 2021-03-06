package br.com.desafiobeca.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiobeca.exceptions.AtualizaPessoaException;
import br.com.desafiobeca.model.Funcionario;
import br.com.desafiobeca.repository.FuncionarioRepository;
import br.com.desafiobeca.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Autowired
	PessoaServiceImpl pessoaService;

	@Override
	public Funcionario salvar(Funcionario funcionario) {
		if (pessoaService.verificarPessoa(funcionario)) {
			throw new EntityExistsException("Uma pessoa com este CPF já existe");
		} else {
			funcionarioRepository.save(funcionario);
			return funcionario;
		}
	}

	@Override
	public Funcionario atualizar(Funcionario funcionario) {
		if (pessoaService.verificarPessoa(funcionario)) {
			return funcionarioRepository.save(funcionario);
		} else { 
			throw new AtualizaPessoaException("Esse funcionario não existe");
		}
	}

	@Override
	public List<Funcionario> listarTodosFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@Override
	public Optional<Funcionario> listarPorId(Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		if (funcionario.isPresent()) {
			return funcionario;
		} else {
			throw new NullPointerException("Funcionario não encontrado");
		}
	}

	@Override
	public Funcionario listarPorCpf(String cpf) {
		Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
		if (funcionario != null) {
			return funcionario;
		} else {
			throw new NullPointerException("Funcionário não encontrado");
		}
	}

	@Override
	public List<Funcionario> listarPorNome(String nome) {
		List<Funcionario> funcionario = new ArrayList<Funcionario>();
		listarTodosFuncionarios().forEach(item -> {
			if (item.getNome().equals(nome)) {
				funcionario.add(item);
			}
		});
		if (funcionario.isEmpty()) {
			throw new NullPointerException("Nenhum funcionário com esse nome encontrado");
		}
		return funcionario;
	}
}
