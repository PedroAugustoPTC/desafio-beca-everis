package br.com.desafiobeca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiobeca.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	public boolean existsByCpf(String cpf);

	public Funcionario findByCpf(String cpf);
}
