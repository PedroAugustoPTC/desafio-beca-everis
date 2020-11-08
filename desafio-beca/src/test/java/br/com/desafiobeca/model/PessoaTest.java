package br.com.desafiobeca.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PessoaTest {

	private Pessoa pessoa;
	private Pessoa pessoa2;

	@BeforeEach
	void setUp() throws Exception {

		pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome("Pedro");
		pessoa.setCpf("143.313.476-48");
		pessoa.setEmail("sddsadas@dasdas");
		pessoa.setTelefone("(034)999506807");

		pessoa2 = new Pessoa();
		pessoa2.setId(1L);
		pessoa2.setNome("Pedro");
		pessoa2.setCpf("143.313.476-48");
		pessoa2.setEmail("sddsadas@dasdas");
		pessoa2.setTelefone("(034)999506807");
	}

	@Test
	void testSetCpf() {
		assertEquals("143.313.476-48", pessoa.getCpf(), "Espera-se o retorno de 143.313.476-48");
	}

	@Test
	void testSetId() {
		assertEquals(1L, pessoa.getId(), "Espera-se 1L");
	}

	@Test
	void testSetNome() {
		assertEquals("Pedro", pessoa.getNome(), "Espera-se Pedro");
	}

	@Test
	void testSetTelefone() {
		assertEquals("(034)999506807", pessoa.getTelefone(), "Espera-se (034)999506807");
	}

	@Test
	void testSetEmail() {
		assertEquals("sddsadas@dasdas", pessoa.getEmail(), "Espera-se sddsadas@dasdas");
	}

	@Test
	void testEqualsObject() {
		boolean teste = pessoa.equals(pessoa2);
		assertEquals(true, teste);
	}

}
