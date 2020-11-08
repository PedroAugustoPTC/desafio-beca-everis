package br.com.desafiobeca.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.desafiobeca.model.Ticket;

public interface TicketService {
	public Ticket salvar(String placa, Integer numeroVaga);

	public List<Ticket> listarTodosTickets();

	public Ticket listarPorId(Long id);

	public List<Ticket> listarPorPlaca(String placa);

	public List<Ticket> listarPorVaga(Integer vaga);

	public String fecharTicket(Long id);

	public Double calculaValorFinal(LocalDateTime horarioEntrada, LocalDateTime horarioSaida);
}
