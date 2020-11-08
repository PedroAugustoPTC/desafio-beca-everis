package br.com.desafiobeca.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FuncionarioTest {

	private Funcionario funcionario;
	private Funcionario funcionario2;

	@BeforeEach
	void setUp() throws Exception {

		funcionario = new Funcionario();
		funcionario.setSalario(321331.321);
	}

	@Test
	void testSetSalario() {
		assertEquals(321331.321, funcionario.getSalario(), "Espera-se 321331.321");
	}

	@Test
	void testEqualsObject() {
		funcionario2 = new Funcionario();
		funcionario2.setSalario(321331.321);
		assertEquals(true, funcionario.equals(funcionario2));
	}

	@Test
	void testHashCode() {
		funcionario2 = new Funcionario();
		funcionario2.setSalario(321331.321);
		assertEquals(funcionario2.hashCode(), funcionario.hashCode());
	}

}
