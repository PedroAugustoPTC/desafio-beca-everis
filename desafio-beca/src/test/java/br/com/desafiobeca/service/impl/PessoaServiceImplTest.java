package br.com.desafiobeca.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome("Pedro");
		pessoa.setCpf("143.313.476-48");
		pessoa.setEmail("sddsadas@dasdas");
		pessoa.setTelefone("(034)999506807");

		lista = new ArrayList<>();
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
		Mockito.when(pessoaService.verificarPessoa(pessoa)).thenReturn(true);
		Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
		assertEquals(pessoa, pessoaService.atualizar(pessoa));

	}

	@Test
	void testAtualizarException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Mockito.when(pessoaService.verificarPessoa(pessoa)).thenReturn(false);
			pessoaService.atualizar(pessoa);
		});

		String mensagemEsperada = "Essa pessoa não existe";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarTodasPessoas() {
		Mockito.when(pessoaRepository.findAll()).thenReturn(lista);
		lista.add(pessoa);
		lista.add(pessoa);
		lista.add(pessoa);
		assertNotNull(pessoaService.listarTodasPessoas());
	}

	@Test
	void testListarTodasPessoasQuandoNaoOuverNinguemNoSistema() {
		Mockito.when(pessoaRepository.findAll()).thenReturn(lista);
		assertNotNull(pessoaService.listarTodasPessoas());
	}

	@Test
	void testListarPorId() {
		Optional<Pessoa> retorno = Optional.of(pessoa);
		Mockito.when(pessoaRepository.findById(pessoa.getId())).thenReturn(retorno);
		assertNotNull(pessoaService.listarPorId(pessoa.getId()));
	}

	@Test
	void testListarPorIdException() {
		assertThrows(NoSuchElementException.class, () -> {
			Mockito.when(pessoaRepository.findById(pessoa.getId()).get()).thenReturn(null);
			pessoaService.listarPorId(pessoa.getId());
		});
	}

	@Test
	void testListarPorNome() {
		lista.add(pessoa);
		lista.add(pessoa);
		lista.add(pessoa);
		lista.add(pessoa);
		Mockito.when(pessoaService.listarTodasPessoas()).thenReturn(lista);
		assertEquals(lista, pessoaService.listarPorNome(pessoa.getNome()));
	}

	@Test
	void testListarPorNomeException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(pessoaService.listarTodasPessoas()).thenReturn(lista);
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
