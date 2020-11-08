package br.com.desafiobeca.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.desafiobeca.model.Pessoa;
import br.com.desafiobeca.model.Ticket;
import br.com.desafiobeca.model.Vaga;
import br.com.desafiobeca.model.Veiculo;
import br.com.desafiobeca.repository.TicketRepository;

class TicketServiceImplTest {

	private Ticket ticket;
	private Ticket ticket2;
	private Veiculo veiculo;
	private Vaga vaga;
	private Vaga vaga2;
	LocalDateTime entrada;
	LocalDateTime saida;
	List<Ticket> lista;
	Pessoa pessoa;

	@InjectMocks
	private TicketServiceImpl ticketService;

	@Mock
	private TicketRepository ticketRepository;

	@Mock
	private VeiculoServiceImpl veiculoService;

	@Mock
	private VagaServiceImpl vagaService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		entrada = LocalDateTime.now();
		saida = LocalDateTime.now().plusHours(1);

		pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome("Pedro");
		pessoa.setCpf("143.313.476-48");
		pessoa.setEmail("sddsadas@dasdas");
		pessoa.setTelefone("(034)999506807");

		veiculo = new Veiculo();
		veiculo.setId(1L);
		veiculo.setPlaca("WER-3456");
		veiculo.setProprietario(pessoa);

		vaga = new Vaga();
		vaga.setId(1L);
		vaga.setNumeroVaga(1);
		vaga.setOcupada(false);

		ticket = new Ticket(veiculo, vaga, entrada);
		ticket.setId(1L);
		ticket.setHorarioSaida(null);
		ticket.setValoTotal(0.0);
	}

	@Test
	void testSalvar() {
		lista = new ArrayList<>();
		lista.add(ticket);
		lista.add(ticket);
		lista.add(ticket);

		vaga2 = new Vaga();
		vaga2.setId(1L);
		vaga2.setNumeroVaga(1);
		vaga2.setOcupada(true);

		Mockito.when(veiculoService.listarVeiculoPorPlaca(ticket.getVeiculo().getPlaca()))
				.thenReturn(ticket.getVeiculo());

		Mockito.when(vagaService.listarPorNumeroVaga(ticket.getVaga().getNumeroVaga())).thenReturn(ticket.getVaga());

		Mockito.when(ticketRepository.findByVeiculo(ticket.getVeiculo())).thenReturn(lista);

		Mockito.when(vagaService.atualizaEstadoVaga(ticket.getVaga().getId())).thenReturn(vaga2);

		Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);

		System.out.println(veiculoService.listarVeiculoPorPlaca(ticket.getVeiculo().getPlaca()));
		System.out.println(vagaService.listarPorNumeroVaga(ticket.getVaga().getNumeroVaga()));
		lista.forEach(item -> {
			System.out.println(item);
		});

		assertEquals(ticket, ticketService.salvar((ticket.getVeiculo().getPlaca()), (ticket.getVaga().getNumeroVaga())));

	}

	@Test
	void testListarTodosTickets() {
		fail("Not yet implemented");
	}

	@Test
	void testListarPorId() {
		fail("Not yet implemented");
	}

	@Test
	void testListarPorPlaca() {
		fail("Not yet implemented");
	}

	@Test
	void testListarPorVaga() {
		fail("Not yet implemented");
	}

	@Test
	void testFecharTicket() {
		fail("Not yet implemented");
	}

	@Test
	void testCalculaValorFinal() {
		fail("Not yet implemented");
	}

}
