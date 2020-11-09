package br.com.desafiobeca.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "tb_ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@NotNull(message = "Veiculo não pode ser nulo")
	private Veiculo veiculo;

	@OneToOne
	@NotNull(message = "Vaga não pode ser nulo")
	private Vaga vaga;

	private LocalDateTime horarioEntrada;

	private LocalDateTime horarioSaida;

	private Double valorTotal;

	public Ticket() {
		super();
	}

	public Ticket(Veiculo veiculo, Vaga vaga, LocalDateTime horarioEntrada) {
		this.veiculo = veiculo;
		this.vaga = vaga;
		this.horarioEntrada = horarioEntrada;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValoTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public LocalDateTime getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(LocalDateTime horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public LocalDateTime getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(LocalDateTime horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horarioEntrada == null) ? 0 : horarioEntrada.hashCode());
		result = prime * result + ((horarioSaida == null) ? 0 : horarioSaida.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((vaga == null) ? 0 : vaga.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Ticket ticket = (Ticket) obj;
		if ((id == ticket.getId()) && (veiculo.equals(ticket.getVeiculo())) && (vaga.equals(ticket.getVaga()))
				&& (horarioEntrada.equals(ticket.getHorarioEntrada()))) {
			return true;
		} else {
			return false;
		}
	}

}
