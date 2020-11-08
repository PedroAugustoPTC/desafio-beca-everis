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

import br.com.desafiobeca.model.Pessoa;
import br.com.desafiobeca.repository.PessoaRepository;

class PessoaServiceImplTest {

	@InjectMocks
	private PessoaServiceImpl pessoaService;

	@Mock
	private PessoaRepository pessoaRepository;

	private Pessoa pessoa;
	List<Pessoa> lista;
	Optional<Pessoa> resultado;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome("Pedro");
		pessoa.setCpf("143.313.476-48");
		pessoa.setEmail("sddsadas@dasdas");
		pessoa.setTelefone("(034)999506807");
	}

	@Test
	void testSalvar() {
		Mockito.when(pessoaRepository.existsByCpf(pessoa.getCpf())).thenReturn(false);
		Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
		assertEquals(pessoa, pessoaService.salvar(pessoa));
	}

	@Test
	void testSalvarException() {
		Exception exception = assertThrows(EntityExistsException.class, () -> {
			Mockito.when(pessoaRepository.existsByCpf(pessoa.getCpf())).thenReturn(true);
			pessoaService.salvar(pessoa);
		});

		String mensagemEsperada = "Uma pessoa com este CPF já existe";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testAtualizar() {
		Mockito.when(pessoaRepository.existsByCpf(pessoa.getCpf())).thenReturn(true);
		Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
		assertEquals(pessoa, pessoaService.atualizar(pessoa));

	}

	@Test
	void testAtualizarException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Mockito.when(pessoaRepository.existsByCpf(pessoa.getCpf())).thenReturn(false);
			pessoaService.atualizar(pessoa);
		});

		String mensagemEsperada = "Essa pessoa não existe";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarTodasPessoas() {
		lista = new ArrayList<>();
		Mockito.when(pessoaRepository.findAll()).thenReturn(lista);
		lista.add(pessoa);
		lista.add(pessoa);
		lista.add(pessoa);
		assertEquals(lista, pessoaService.listarTodasPessoas());
	}

	@Test
	void testListarTodasPessoasQuandoNaoOuverNinguemNoSistema() {
		lista = new ArrayList<>();
		Mockito.when(pessoaRepository.findAll()).thenReturn(lista);
		assertEquals(lista, pessoaService.listarTodasPessoas());
	}

	@Test
	void testListarPorId() {
		resultado = Optional.of(pessoa);
		Mockito.when(pessoaRepository.findById(pessoa.getId())).thenReturn(resultado);
		assertEquals(resultado, pessoaService.listarPorId(pessoa.getId()));
	}

	@Test
	void testListarPorIdException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(pessoaRepository.findById(pessoa.getId())).thenReturn(Optional.empty());
			pessoaService.listarPorId(pessoa.getId());
		});

		String mensagemEsperada = "Pessoa não encontrada";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarPorNome() {
		lista = new ArrayList<>();
		lista.add(pessoa);
		lista.add(pessoa);
		lista.add(pessoa);
		lista.add(pessoa);
		Mockito.when(pessoaRepository.findAll()).thenReturn(lista);
		assertEquals(lista, pessoaService.listarPorNome(pessoa.getNome()));
	}

	@Test
	void testListarPorNomeException() {
		lista = new ArrayList<>();
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(pessoaRepository.findAll()).thenReturn(lista);
			assertEquals(lista, pessoaService.listarPorNome(pessoa.getNome()));
		});

		String mensagemEsperada = "Nenhum nome encontrado";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarPorCpf() {
		Mockito.when(pessoaRepository.findByCpf(pessoa.getCpf())).thenReturn(pessoa);
		assertEquals(pessoa, pessoaService.listarPorCpf(pessoa.getCpf()));
	}

	@Test
	void testListarPorCpfException() {
		assertThrows(NullPointerException.class, () -> {
			Mockito.when(pessoaRepository.findByCpf(pessoa.getCpf())).thenReturn(null);
			pessoaService.listarPorCpf(pessoa.getCpf());
		});
	}

	@Test
	void testVerificarPessoa() {
		Mockito.when(pessoaRepository.existsByCpf(pessoa.getCpf())).thenReturn(true);
		assertEquals(true, pessoaService.verificarPessoa(pessoa));
	}

	@Test
	void testVerificarPessoaFalso() {
		Mockito.when(pessoaRepository.existsByCpf(pessoa.getCpf())).thenReturn(false);
		assertEquals(false, pessoaService.verificarPessoa(pessoa));
	}

}
