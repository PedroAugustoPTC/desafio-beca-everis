package br.com.desafiobeca.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.desafiobeca.exceptions.TicketInvalidoException;
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
	private LocalDateTime entrada;
	private List<Ticket> lista;
	private Pessoa pessoa;
	private Optional<Ticket> resultado;

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
	}

	@Test
	void testSalvar() {
		ticket2 = new Ticket(veiculo, vaga2, entrada);
		ticket2.setId(1L);
		ticket2.setHorarioSaida(LocalDateTime.now());
		ticket2.setValoTotal(0.0);

		lista = new ArrayList<>();
		lista.add(ticket2);

		vaga2 = new Vaga();
		vaga2.setId(1L);
		vaga2.setNumeroVaga(1);
		vaga2.setOcupada(true);

		ticket.setVaga(vaga2);

		Mockito.when(veiculoService.listarVeiculoPorPlaca(veiculo.getPlaca())).thenReturn(veiculo);
		Mockito.when(vagaService.listarPorNumeroVaga(vaga.getNumeroVaga())).thenReturn(vaga);
		Mockito.when(ticketRepository.findByVeiculo(veiculo)).thenReturn(lista);
		Mockito.when(vagaService.atualizaEstadoVaga(vaga.getId())).thenReturn(vaga2);
		Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);

		assertEquals(ticket, ticketService.salvar(veiculo.getPlaca(), vaga.getNumeroVaga()));

	}

	@Test
	void testSalvarTicketInvalidoException() {
		ticket.setHorarioSaida(null);
		lista = new ArrayList<>();
		lista.add(ticket);

		vaga2 = new Vaga();
		vaga2.setId(1L);
		vaga2.setNumeroVaga(1);
		vaga2.setOcupada(true);

		Exception exception = assertThrows(TicketInvalidoException.class, () -> {
			Mockito.when(veiculoService.listarVeiculoPorPlaca(veiculo.getPlaca())).thenReturn(veiculo);
			Mockito.when(vagaService.listarPorNumeroVaga(vaga.getNumeroVaga())).thenReturn(vaga);
			Mockito.when(ticketRepository.findByVeiculo(veiculo)).thenReturn(lista);
		});

		String mensagemEsperada = "Este veículo já está com um ticket em aberto";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarTodosTickets() {
		lista = new ArrayList<>();
		lista.add(ticket);
		lista.add(ticket);
		lista.add(ticket);
		Mockito.when(ticketRepository.findAll()).thenReturn(lista);
		assertEquals(lista, ticketService.listarTodosTickets());
	}

	@Test
	void testListarPorId() {
		resultado = Optional.of(ticket);
		Mockito.when(ticketRepository.findById(ticket.getId())).thenReturn(resultado);
		assertEquals(resultado, ticketService.listarPorId(ticket.getId()));
	}

	@Test
	void testListarPorIdException() {
		resultado = Optional.of(ticket);
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.empty());
			assertEquals(resultado, ticketService.listarPorId(ticket.getId()));
		});

		String mensagemEsperada = "Ticket não encontrado";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarPorPlaca() {
		lista = new ArrayList<>();
		lista.add(ticket);
		lista.add(ticket);
		lista.add(ticket);

		Mockito.when(ticketRepository.findByVeiculo(ticket.getVeiculo())).thenReturn(lista);
		Mockito.when(veiculoService.listarVeiculoPorPlaca(ticket.getVeiculo().getPlaca()))
				.thenReturn(ticket.getVeiculo());
		assertEquals(lista, ticketService.listarPorPlaca(ticket.getVeiculo().getPlaca()));
	}

	@Test
	void testListarPorPlacaException() {
		lista = new ArrayList<>();
		lista.add(ticket);
		lista.add(ticket);
		lista.add(ticket);

		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(ticketRepository.findByVeiculo(ticket.getVeiculo())).thenReturn(null);
			Mockito.when(veiculoService.listarVeiculoPorPlaca(ticket.getVeiculo().getPlaca()))
					.thenReturn(ticket.getVeiculo());
			assertEquals(lista, ticketService.listarPorPlaca(ticket.getVeiculo().getPlaca()));
		});

		String mensagemEsperada = "Ticket não encontrado";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarPorVaga() {
		lista = new ArrayList<>();
		lista.add(ticket);
		lista.add(ticket);
		lista.add(ticket);

		Mockito.when(ticketRepository.findByVaga(ticket.getVaga())).thenReturn(lista);
		Mockito.when(vagaService.listarPorNumeroVaga(ticket.getVaga().getNumeroVaga())).thenReturn(ticket.getVaga());
		assertEquals(lista, ticketService.listarPorVaga(ticket.getVaga().getNumeroVaga()));
	}

	@Test
	void testListarPorVagaException() {
		lista = new ArrayList<>();
		lista.add(ticket);
		lista.add(ticket);
		lista.add(ticket);

		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(ticketRepository.findByVaga(ticket.getVaga())).thenReturn(null);
			Mockito.when(vagaService.listarPorNumeroVaga(ticket.getVaga().getNumeroVaga()))
					.thenReturn(ticket.getVaga());
			assertEquals(lista, ticketService.listarPorVaga(ticket.getVaga().getNumeroVaga()));
		});

		String mensagemEsperada = "Ticket não encontrado";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testFecharTicket() {
		vaga2 = new Vaga();
		vaga2.setId(1L);
		vaga2.setNumeroVaga(1);
		vaga2.setOcupada(true);

		ticket2 = new Ticket(veiculo, vaga, entrada);
		ticket2.setId(1L);
		ticket2.setHorarioSaida(null);
		ticket2.setValoTotal(0.0);

		resultado = Optional.of(ticket2);
		Mockito.when(ticketRepository.findById(ticket.getId())).thenReturn(resultado);
		Mockito.when(vagaService.atualizaEstadoVaga(ticket.getVaga().getId())).thenReturn(vaga2);
		assertEquals("Ticket fechado, o valor de pagamento é: " + ticket2.getValorTotal(),
				ticketService.fecharTicket(ticket2.getId()));
	}

	@Test
	void testFecharTicketQueJaFoiFechado() {
		vaga2 = new Vaga();
		vaga2.setId(1L);
		vaga2.setNumeroVaga(1);
		vaga2.setOcupada(true);

		ticket.setHorarioSaida(LocalDateTime.now());

		resultado = Optional.of(ticket);
		Mockito.when(ticketRepository.findById(ticket.getId())).thenReturn(resultado);
		Mockito.when(vagaService.atualizaEstadoVaga(ticket.getVaga().getId())).thenReturn(vaga2);
		assertEquals("Esse ticket já foi fechado", ticketService.fecharTicket(ticket.getId()));
	}

	@Test
	void testFecharTicketException() {
		vaga2 = new Vaga();
		vaga2.setId(1L);
		vaga2.setNumeroVaga(1);
		vaga2.setOcupada(true);

		resultado = Optional.of(ticket);
		assertThrows(NoSuchElementException.class, () -> {
			Mockito.when(ticketRepository.findById(ticket.getId())).thenReturn(Optional.empty());
			Mockito.when(vagaService.atualizaEstadoVaga(ticket.getVaga().getId())).thenReturn(vaga2);
			assertEquals("Ticket fechado, o valor de pagamento é: " + ticket.getValorTotal(),
					ticketService.fecharTicket(ticket.getId()));
		});
	}

}
