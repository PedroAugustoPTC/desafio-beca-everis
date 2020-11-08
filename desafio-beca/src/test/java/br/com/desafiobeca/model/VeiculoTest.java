package br.com.desafiobeca.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VeiculoTest {

	private Veiculo veiculo;
	private Pessoa pessoa;

	@BeforeEach
	void setUp() throws Exception {
		veiculo = new Veiculo();
		pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome("Pedro");
		pessoa.setCpf("143.313.476-48");
		pessoa.setEmail("sddsadas@dasdas");
		pessoa.setTelefone("(034)999506807");

		veiculo.setId(1L);
		veiculo.setPlaca("WER-3456");
		veiculo.setProprietario(pessoa);
	}

	@Test
	void testSetProprietario() {
		assertEquals(pessoa, veiculo.getProprietario());
	}

	@Test
	void testSetId() {
		assertEquals(1L, veiculo.getId());
	}

	@Test
	void testSetPlaca() {
		assertEquals("WER-3456", veiculo.getPlaca());
	}

	@Test
	void testEqualsObject() {
		boolean teste = veiculo.equals(veiculo);
		assertEquals(true, teste);
	}

}
