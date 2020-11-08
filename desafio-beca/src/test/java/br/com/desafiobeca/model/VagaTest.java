package br.com.desafiobeca.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VagaTest {

	private Vaga vaga;
	private Vaga vaga2;

	@BeforeEach
	void setUp() throws Exception {
		vaga = new Vaga();
		vaga.setId(1L);
		vaga.setNumeroVaga(1);
		vaga.setOcupada(false);
	}

	@Test
	void testSetNumeroVaga() {
		assertEquals(1, vaga.getNumeroVaga());
	}

	@Test
	void testSetOcupada() {
		assertEquals(false, vaga.isOcupada());
	}

	@Test
	void testSetId() {
		assertEquals(1L, vaga.getId());
	}

	@Test
	void testEqualsObject() {
		vaga2 = new Vaga();
		vaga2.setId(1L);
		vaga2.setNumeroVaga(1);
		vaga2.setOcupada(false);
		assertEquals(true, vaga.equals(vaga2));
	}

	@Test
	void testHashCode() {
		vaga2 = new Vaga();
		vaga2.setId(1L);
		vaga2.setNumeroVaga(1);
		vaga2.setOcupada(false);
		assertEquals(vaga2.hashCode(), vaga.hashCode());
	}

}
