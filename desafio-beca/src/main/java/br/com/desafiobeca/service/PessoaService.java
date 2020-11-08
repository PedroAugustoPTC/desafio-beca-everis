package br.com.desafiobeca.service;

import java.util.List;

import br.com.desafiobeca.model.Pessoa;

public interface PessoaService {
	public Pessoa salvar(Pessoa pessoa);

	public Pessoa atualizar(Pessoa pessoa);

	public List<Pessoa> listarTodasPessoas();

	public Pessoa listarPorId(Long id);

	public List<Pessoa> listarPorNome(String nome);

	public Pessoa listarPorCpf(String cpf);

	public boolean verificarPessoa(Pessoa pessoa);
}
