package br.com.desafiobeca.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiobeca.exceptions.TicketInvalidoException;
import br.com.desafiobeca.model.Ticket;
import br.com.desafiobeca.model.Vaga;
import br.com.desafiobeca.model.Veiculo;
import br.com.desafiobeca.repository.TicketRepository;
import br.com.desafiobeca.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	VeiculoServiceImpl veiculoServiceImpl;

	@Autowired
	VagaServiceImpl vagaServiceImpl;

	public Ticket salvar(String placa, Integer numeroVaga) {
		Veiculo veiculo = veiculoServiceImpl.listarVeiculoPorPlaca(placa);
		Vaga vaga = vagaServiceImpl.listarPorNumeroVaga(numeroVaga);

		List<Ticket> testeBanco = ticketRepository.findByVeiculo(veiculo);
		testeBanco.forEach(item -> {
			if (item.getVaga().isOcupada())
				throw new TicketInvalidoException("Este veículo já está com um ticket em aberto");
		});

		if (veiculo != null && vaga != null && !(vaga.isOcupada())) {
			vaga = vagaServiceImpl.atualizaEstadoVaga(vaga.getId());
			Ticket ticket = new Ticket(veiculo, vaga, LocalDateTime.now());
			return ticketRepository.save(ticket);
		} else {
			throw new NullPointerException(
					"Verifique se a placa ou a vaga estão corretos. Lembre-se de que o mesmo veículo "
							+ "não pode ocupar duas vagas ao mesmo e verifique se a vaga selecionada já não está ocupada");
		}
	}

	public List<Ticket> listarTodosTickets() {
		return ticketRepository.findAll();
	}

	public Ticket listarPorId(Long id) {
		Ticket ticket = ticketRepository.findById(id).get();
		if (ticket != null) {
			return ticket;
		} else {
			throw new NullPointerException("Ticket não encontrado");
		}
	}

	public List<Ticket> listarPorPlaca(String placa) {
		List<Ticket> ticket = ticketRepository.findByVeiculo(veiculoServiceImpl.listarVeiculoPorPlaca(placa));
		if (ticket != null) {
			return ticket;
		} else {
			throw new NullPointerException("Ticket não encontrado");
		}
	}

	public List<Ticket> listarPorVaga(Integer vaga) {
		List<Ticket> ticket = ticketRepository.findByVaga(vagaServiceImpl.listarPorNumeroVaga(vaga));
		if (ticket != null) {
			return ticket;
		} else {
			throw new NullPointerException("Ticket não encontrado");
		}
	}

	public String fecharTicket(Long id) {
		Ticket ticket = ticketRepository.findById(id).get();
		if (ticket.getHorarioSaida() != null) {
			return "Esse ticket já foi fechado";
		}
		if (ticket != null && ticket.getHorarioSaida() == null) {
			ticket.setHorarioSaida(LocalDateTime.now());
			ticket.setValoTotal(calculaValorFinal(ticket.getHorarioEntrada(), ticket.getHorarioSaida()));
			vagaServiceImpl.atualizaEstadoVaga(ticket.getVaga().getId());
			return "Ticket fechado, o valor de pagamento é: "
					+ calculaValorFinal(ticket.getHorarioEntrada(), ticket.getHorarioSaida());
		} else {
			throw new TicketInvalidoException("Este ticket não foi encontrado");
		}
	}

	public Double calculaValorFinal(LocalDateTime horarioEntrada, LocalDateTime horarioSaida) {
		Integer entradaMinutos;
		Integer saidaMinutos;
		final Double valorMinuto = 0.05;

		entradaMinutos = horarioEntrada.getHour() * 60;
		saidaMinutos = horarioSaida.getHour() * 60;
		entradaMinutos += horarioEntrada.getMinute();
		saidaMinutos += horarioSaida.getMinute();

		return (saidaMinutos - entradaMinutos) * valorMinuto;
	}
}
