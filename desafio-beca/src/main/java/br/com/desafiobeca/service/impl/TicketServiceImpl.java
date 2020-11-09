package br.com.desafiobeca.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

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

	@Override
	public Ticket salvar(String placa, Integer numeroVaga) {
		Veiculo veiculo = veiculoServiceImpl.listarVeiculoPorPlaca(placa);
		Vaga vaga = vagaServiceImpl.listarPorNumeroVaga(numeroVaga);

		List<Ticket> listaTicket = ticketRepository.findByVeiculo(veiculo);
		listaTicket.forEach(item -> {
			if (item.getHorarioSaida() == null) {
				throw new TicketInvalidoException("Este veículo já está com um ticket em aberto");
			}
		});

		if (vaga.isOcupada()) {
			throw new EntityExistsException("Verifique se a vaga selecionada já não está ocupada");

		} else {
			Ticket ticket = new Ticket(veiculo, vaga, LocalDateTime.now());
			Ticket retorno = ticketRepository.save(ticket);
			vagaServiceImpl.atualizaEstadoVaga(vaga.getId());
			return retorno;
		}
	}

	@Override
	public List<Ticket> listarTodosTickets() {
		return ticketRepository.findAll();
	}

	public Optional<Ticket> listarPorId(Long id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		if (ticket.isPresent()) {
			return ticket;
		} else {
			throw new NullPointerException("Ticket não encontrado");
		}
	}

	@Override
	public List<Ticket> listarPorPlaca(String placa) {
		List<Ticket> ticket = ticketRepository.findByVeiculo(veiculoServiceImpl.listarVeiculoPorPlaca(placa));
		if (ticket != null) {
			return ticket;
		} else {
			throw new NullPointerException("Ticket não encontrado");
		}
	}

	@Override
	public List<Ticket> listarPorVaga(Integer vaga) {
		List<Ticket> ticket = ticketRepository.findByVaga(vagaServiceImpl.listarPorNumeroVaga(vaga));
		if (ticket != null) {
			return ticket;
		} else {
			throw new NullPointerException("Ticket não encontrado");
		}
	}

	@Override
	public String fecharTicket(Long id) {
		Optional<Ticket> optionalTicket = ticketRepository.findById(id);
		Ticket ticket = optionalTicket.get();
		if (ticket.getHorarioSaida() != null) {
			return "Esse ticket já foi fechado";
		} else {
			ticket.setHorarioSaida(LocalDateTime.now());
			ticket.setValoTotal(calculaValorFinal(ticket.getHorarioEntrada(), ticket.getHorarioSaida()));
			vagaServiceImpl.atualizaEstadoVaga(ticket.getVaga().getId());
			return "Ticket fechado, o valor de pagamento é: " + ticket.getValorTotal();
		}
	}

	@Override
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
