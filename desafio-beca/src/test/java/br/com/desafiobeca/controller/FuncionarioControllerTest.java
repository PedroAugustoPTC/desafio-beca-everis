package br.com.desafiobeca.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.desafiobeca.model.Funcionario;
import br.com.desafiobeca.service.impl.FuncionarioServiceImpl;

class FuncionarioControllerTest {
	private Funcionario funcionario;
	private Optional<Funcionario> resultado;

	@InjectMocks
	private FuncionarioController funcionarioController;

	@Mock
	private FuncionarioServiceImpl funcionarioService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		funcionario = new Funcionario();
		funcionario.setId(1L);
		funcionario.setNome("Pedro");
		funcionario.setCpf("143.313.476-48");
		funcionario.setEmail("sddsadas@dasdas");
		funcionario.setTelefone("(034)999506807");
		funcionario.setSalario(3.0);

		resultado = Optional.of(funcionario);
	}

	@Test
	void testSalvar() {
		Mockito.when(funcionarioService.salvar(funcionario)).thenReturn(funcionario);
		assertNotNull(funcionarioController.salvar(funcionario));
	}

	@Test
	void testAtualizar() {
		Mockito.when(funcionarioService.atualizar(funcionario)).thenReturn(funcionario);
		assertNotNull(funcionarioController.atualizar(funcionario));
	}

	@Test
	void testListarTodosFuncionarios() {
		List<Funcionario> lista = new ArrayList<>();
		lista.add(funcionario);
		Mockito.when(funcionarioService.listarTodosFuncionarios()).thenReturn(lista);
		assertNotNull(funcionarioController.listarTodosFuncionarios());
	}

	@Test
	void testListarFuncionarioPorId() {
		Mockito.when(funcionarioService.listarPorId(funcionario.getId())).thenReturn(resultado);
		assertNotNull(funcionarioController.listarFuncionarioPorId(funcionario.getId()));
	}

	@Test
	void testListarFuncionarioPorNome() {
		List<Funcionario> lista = new ArrayList<>();
		lista.add(funcionario);
		Mockito.when(funcionarioService.listarPorNome(funcionario.getNome())).thenReturn(lista);
		assertNotNull(funcionarioController.listarFuncionarioPorNome(funcionario.getNome()));
	}

	@Test
	void testListarFuncionarioPorCpf() {
		Mockito.when(funcionarioService.listarPorCpf(funcionario.getCpf())).thenReturn(funcionario);
		assertNotNull(funcionarioController.listarFuncionarioPorCpf(funcionario.getCpf()));
	}

}
