package br.com.desafiobeca.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketTest {

	private Ticket ticket;
	private Veiculo veiculo;
	private Vaga vaga;
	LocalDateTime entrada;
	LocalDateTime saida;

	@BeforeEach
	void setUp() throws Exception {
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
	}

	@Test
	void testTicketVeiculoVagaLocalDateTime() {
		assertEquals(veiculo, ticket.getVeiculo());
		assertEquals(vaga, ticket.getVaga());
		assertEquals(ticket.getHorarioEntrada(), ticket.getHorarioEntrada());
	}

	@Test
	void testSetValoTotal() {
		assertEquals(3.0, ticket.getValorTotal());
	}

	@Test
	void testSetId() {
		assertEquals(1L, ticket.getId());
	}

	@Test
	void testSetVeiculo() {
		assertEquals(veiculo, ticket.getVeiculo());
	}

	@Test
	void testSetVaga() {
		assertEquals(vaga, ticket.getVaga());
	}

	@Test
	void testSetHorarioEntrada() {
		assertEquals(entrada, ticket.getHorarioEntrada());
	}

	@Test
	void testSetHorarioSaida() {
		assertEquals(saida, ticket.getHorarioSaida());
	}

}
