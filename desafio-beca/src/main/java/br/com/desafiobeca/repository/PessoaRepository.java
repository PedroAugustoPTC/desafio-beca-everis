package br.com.desafiobeca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiobeca.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public boolean existsByCpf(String cpf);

	public Pessoa findByCpf(String cpf);
}
