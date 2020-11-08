package br.com.desafiobeca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiobeca.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	public boolean existsByPlaca(String placa);

	public Veiculo findByPlaca(String placa);

}
