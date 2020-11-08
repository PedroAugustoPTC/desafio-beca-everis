package br.com.desafiobeca.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VeiculoTest {

	private Veiculo veiculo;
	private Veiculo veiculo2;
	private Pessoa pessoa;

	@BeforeEach
	void setUp() throws Exception {
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
		veiculo2 = new Veiculo();
		veiculo2.setId(1L);
		veiculo2.setPlaca("WER-3456");
		veiculo2.setProprietario(pessoa);
		assertEquals(true, veiculo.equals(veiculo2));
	}

	@Test
	void testHashCode() {
		veiculo2 = new Veiculo();
		veiculo2.setId(1L);
		veiculo2.setPlaca("WER-3456");
		veiculo2.setProprietario(pessoa);
		assertEquals(veiculo2.hashCode(), veiculo.hashCode());
	}

}
