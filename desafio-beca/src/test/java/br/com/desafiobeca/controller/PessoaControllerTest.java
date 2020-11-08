package br.com.desafiobeca.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.desafiobeca.model.Pessoa;
import br.com.desafiobeca.service.impl.PessoaServiceImpl;

class PessoaControllerTest {

	@InjectMocks
	private PessoaController pessoaController;

	@Mock
	private PessoaServiceImpl pessoaService;

	private Pessoa pessoa;

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
		Mockito.when(pessoaService.salvar(pessoa)).thenReturn(pessoa);
		assertNotNull(pessoaController.salvar(pessoa));
	}

	@Test
	void testAtualizar() {
		Mockito.when(pessoaService.atualizar(pessoa)).thenReturn(pessoa);

		assertNotNull(pessoaController.atualizar(pessoa));
	}

	@Test
	void testListarTodasPessoas() {
		List<Pessoa> lista = new ArrayList<>();
		lista.add(pessoa);
		Mockito.when(pessoaService.listarTodasPessoas()).thenReturn(lista);

		assertNotNull(pessoaController.listarTodasPessoas());
	}

	@Test
	void testListarPessoaPorId() {
		Mockito.when(pessoaService.listarPorId(pessoa.getId())).thenReturn(pessoa);
		assertNotNull(pessoaController.listarPessoaPorId(pessoa.getId()));
	}

	@Test
	void testListarPessoaPorNome() {
		List<Pessoa> lista = new ArrayList<>();
		lista.add(pessoa);
		Mockito.when(pessoaService.listarPorNome(pessoa.getNome())).thenReturn(lista);
		assertNotNull(pessoaController.listarPessoaPorNome(pessoa.getNome()));
	}

	@Test
	void testListarPessoaPorCpf() {
		Mockito.when(pessoaService.listarPorCpf(pessoa.getCpf())).thenReturn(pessoa);
		assertNotNull(pessoaController.listarPessoaPorCpf(pessoa.getCpf()));
	}

}
