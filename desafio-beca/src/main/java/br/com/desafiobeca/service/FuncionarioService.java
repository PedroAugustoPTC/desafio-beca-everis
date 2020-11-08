package br.com.desafiobeca.service;

import java.util.List;
import java.util.Optional;

import br.com.desafiobeca.model.Funcionario;

public interface FuncionarioService {
	public Funcionario salvar(Funcionario funcionario);

	public Funcionario atualizar(Funcionario funcionario);

	public List<Funcionario> listarTodosFuncionarios();

	public Optional<Funcionario> listarPorId(Long id);

	public Funcionario listarPorCpf(String cpf);

	public List<Funcionario> listarPorNome(String nome);
}
