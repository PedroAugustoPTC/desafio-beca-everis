package br.com.desafiobeca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiobeca.model.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

	public boolean existsByNumeroVaga(Integer numeroVaga);

	public Vaga findByNumeroVaga(Integer numeroVaga);

	public List<Vaga> findByOcupada(boolean ocupado);
}
