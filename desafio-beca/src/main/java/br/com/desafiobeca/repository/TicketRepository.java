package br.com.desafiobeca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiobeca.model.Ticket;
import br.com.desafiobeca.model.Vaga;
import br.com.desafiobeca.model.Veiculo;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	public List<Ticket> findByVeiculo(Veiculo veiculo);

	public List<Ticket> findByVaga(Vaga vaga);

	public boolean existsByVeiculo(Veiculo veiculo);

}
