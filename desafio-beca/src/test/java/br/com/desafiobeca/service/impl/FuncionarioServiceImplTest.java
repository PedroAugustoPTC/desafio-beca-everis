package br.com.desafiobeca.service.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.desafiobeca.exceptions.AtualizaPessoaException;
import br.com.desafiobeca.model.Funcionario;
import br.com.desafiobeca.repository.FuncionarioRepository;

class FuncionarioServiceImplTest {

	private Funcionario funcionario;
	private List<Funcionario> lista;
	private Optional<Funcionario> resultado;

	@InjectMocks
	private FuncionarioServiceImpl funcionarioService;

	@Mock
	private FuncionarioRepository funcionarioRepository;

	@Mock
	private PessoaServiceImpl pessoaService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		funcionario = new Funcionario();
		funcionario.setId(1L);
		funcionario.setNome("Pedro");
		funcionario.setCpf("143.313.476-48");
		funcionario.setEmail("sddsadas@dasdas");
		funcionario.setTelefone("(034)999506807");
		funcionario.setSalario(2.0);
	}

	@Test
	void testSalvar() {
		Mockito.when(pessoaService.verificarPessoa(funcionario)).thenReturn(false);
		Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
		assertEquals(funcionario, funcionarioService.salvar(funcionario));
	}

	@Test
	void testSalvarException() {
		Exception exception = assertThrows(EntityExistsException.class, () -> {
			Mockito.when(pessoaService.verificarPessoa(funcionario)).thenReturn(true);
			funcionarioService.salvar(funcionario);
		});

		String mensagemEsperada = "Uma pessoa com este CPF já existe";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testAtualizar() {
		Mockito.when(pessoaService.verificarPessoa(funcionario)).thenReturn(true);
		Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
		assertEquals(funcionario, funcionarioService.atualizar(funcionario));

	}

	@Test
	void testAtualizarException() {
		Exception exception = assertThrows(AtualizaPessoaException.class, () -> {
			Mockito.when(pessoaService.verificarPessoa(funcionario)).thenReturn(false);
			funcionarioService.atualizar(funcionario);
		});

		String mensagemEsperada = "Esse funcionario não existe";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarTodosFuncionarios() {
		lista = new ArrayList<>();
		lista.add(funcionario);
		lista.add(funcionario);
		lista.add(funcionario);

		Mockito.when(funcionarioRepository.findAll()).thenReturn(lista);
		assertEquals(lista, funcionarioService.listarTodosFuncionarios());
	}

	@Test
	void testListarTodosFuncionariosQuandoNaoOuverNinguemNoSistema() {
		lista = new ArrayList<>();
		Mockito.when(funcionarioRepository.findAll()).thenReturn(lista);
		assertEquals(lista, funcionarioService.listarTodosFuncionarios());
	}

	@Test
	void testListarPorId() {
		resultado = Optional.of(funcionario);

		Mockito.when(funcionarioRepository.findById(funcionario.getId())).thenReturn(resultado);
		assertEquals(resultado, funcionarioService.listarPorId(funcionario.getId()));
	}

	@Test
	void testListarPorIdException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(funcionarioRepository.findById(funcionario.getId())).thenReturn(Optional.empty());
			funcionarioService.listarPorId(funcionario.getId());
		});

		String mensagemEsperada = "Funcionario não encontrado";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarPorCpf() {
		Mockito.when(funcionarioRepository.findByCpf(funcionario.getCpf())).thenReturn(funcionario);
		assertEquals(funcionario, funcionarioService.listarPorCpf(funcionario.getCpf()));
	}

	@Test
	void testListarPorCpfException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(funcionarioRepository.findByCpf(funcionario.getCpf())).thenReturn(null);
			funcionarioService.listarPorCpf(funcionario.getCpf());
		});

		String mensagemEsperada = "Funcionário não encontrado";
		String mensagemAtual = exception.getMessage();
		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testarPorNome() {
		lista = new ArrayList<>();
		lista.add(funcionario);
		lista.add(funcionario);
		lista.add(funcionario);
		lista.add(funcionario);

		Mockito.when(funcionarioRepository.findAll()).thenReturn(lista);
		assertEquals(lista, funcionarioService.listarPorNome(funcionario.getNome()));
	}

	@Test
	void testListarPorNomeException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			lista = new ArrayList<>();
			Mockito.when(funcionarioRepository.findAll()).thenReturn(lista);
			assertEquals(lista, funcionarioService.listarPorNome(funcionario.getNome()));
		});

		String mensagemEsperada = "Nenhum funcionário com esse nome encontrado";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

}
