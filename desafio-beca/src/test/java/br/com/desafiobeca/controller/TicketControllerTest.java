package br.com.desafiobeca.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.desafiobeca.model.Ticket;
import br.com.desafiobeca.model.Vaga;
import br.com.desafiobeca.model.Veiculo;
import br.com.desafiobeca.model.dto.TicketDtoSalvar;
import br.com.desafiobeca.service.impl.TicketServiceImpl;

class TicketControllerTest {

	private Ticket ticket;
	private TicketDtoSalvar ticketSalvar;
	private Veiculo veiculo;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private List<Ticket> lista;
	private Optional<Ticket> resultado;

	@InjectMocks
	private TicketController ticketController;

	@Mock
	private TicketServiceImpl ticketService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		entrada = LocalDateTime.now();
		saida = LocalDateTime.now().plusHours(1);

		veiculo = new Veiculo();
		veiculo.setId(1L);
		veiculo.setPlaca("WER-3456");
		veiculo.setProprietario(null);

		vaga = new Vaga();
		vaga.setId(1L);
		vaga.setNumeroVaga(1);
		vaga.setOcupada(true);

		ticket = new Ticket(veiculo, vaga, entrada);
		ticket.setId(1L);
		ticket.setValoTotal(3.0);
		ticket.setHorarioSaida(saida);

		ticketSalvar = new TicketDtoSalvar();

		lista = new ArrayList<>();
		lista.add(ticket);

		resultado = Optional.of(ticket);
	}

	@Test
	void testSalvar() {
		Mockito.when(ticketService.salvar(veiculo.getPlaca(), vaga.getNumeroVaga())).thenReturn(ticket);
		assertNotNull(ticketController.salvar(ticketSalvar));
	}

	@Test
	void testFecharTicket() {
		Mockito.when(ticketService.fecharTicket(ticket.getId()))
				.thenReturn("Ticket fechado, o valor de pagamento é: " + 0.0);
		assertNotNull(ticketController.fecharTicket(ticket.getId()));
	}

	@Test
	void testListarTodosTickets() {
		Mockito.when(ticketService.listarTodosTickets()).thenReturn(lista);
		assertNotNull(ticketController.listarTodosTickets());
	}

	@Test
	void testListarTicketPorId() {
		Mockito.when(ticketService.listarPorId(ticket.getId())).thenReturn(resultado);
		assertNotNull(ticketController.listarTicketPorId(ticket.getId()));
	}

	@Test
	void testListarTicketPorPlaca() {
		Mockito.when(ticketService.listarPorPlaca(ticket.getVeiculo().getPlaca())).thenReturn(lista);
		assertNotNull(ticketController.listarTicketPorPlaca(ticket.getVeiculo().getPlaca()));
	}

	@Test
	void testListarTicketPorVaga() {
		Mockito.when(ticketService.listarPorVaga(ticket.getVaga().getNumeroVaga())).thenReturn(lista);
		assertNotNull(ticketController.listarTicketPorVaga(ticket.getVaga().getNumeroVaga()));
	}

}
