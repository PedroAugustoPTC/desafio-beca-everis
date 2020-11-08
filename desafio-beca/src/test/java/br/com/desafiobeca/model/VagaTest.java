package br.com.desafiobeca.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VagaTest {

	private Vaga vaga;

	@BeforeEach
	void setUp() throws Exception {
		vaga = new Vaga();
		vaga.setId(1L);
		vaga.setNumeroVaga(1);
		vaga.setOcupada(true);
	}

	@Test
	void testSetNumeroVaga() {
		assertEquals(1, vaga.getNumeroVaga());
	}

	@Test
	void testSetOcupada() {
		assertEquals(true, vaga.isOcupada());
	}

	@Test
	void testSetId() {
		assertEquals(1L, vaga.getId());
	}

}
