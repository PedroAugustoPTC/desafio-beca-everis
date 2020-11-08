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

import br.com.desafiobeca.model.Vaga;
import br.com.desafiobeca.repository.VagaRepository;

class VagaServiceImplTest {

	private Vaga vaga;
	private List<Vaga> lista;
	private Optional<Vaga> resultado;

	@InjectMocks
	private VagaServiceImpl vagaService;

	@Mock
	private VagaRepository vagaRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		vaga = new Vaga();
		vaga.setId(1L);
		vaga.setNumeroVaga(1);
		vaga.setOcupada(false);

		lista = new ArrayList<>();
		resultado = Optional.of(vaga);
	}

	@Test
	void testSalvarRetornaObjetoDaClasseVaga() {
		Mockito.when(vagaService.verificaVaga(vaga.getNumeroVaga())).thenReturn(false);
		assertEquals(vaga.getNumeroVaga(), vagaService.salvar(vaga.getNumeroVaga()).getNumeroVaga());
		assertEquals(vaga.isOcupada(), vagaService.salvar(vaga.getNumeroVaga()).isOcupada());
	}

	@Test
	void testSalvarException() {
		Exception exception = assertThrows(EntityExistsException.class, () -> {
			Mockito.when(vagaService.verificaVaga(vaga.getNumeroVaga())).thenReturn(true);
			vagaService.salvar(vaga.getNumeroVaga());
		});

		String mensagemEsperada = "Esse número de vaga já existe";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarTodasVagas() {
		lista.add(vaga);
		lista.add(vaga);
		lista.add(vaga);
		Mockito.when(vagaRepository.findAll()).thenReturn(lista);
		assertEquals(lista, vagaService.listarTodasVagas());
	}

	@Test
	void testListarTodasVagasQuandoNaoOuverNinguemNoSistema() {
		Mockito.when(vagaRepository.findAll()).thenReturn(lista);
		assertEquals(lista, vagaService.listarTodasVagas());
	}

	@Test
	void testAtualizar() {
		Mockito.when(vagaRepository.findById(vaga.getId())).thenReturn(resultado);
		Mockito.when(vagaRepository.save(vaga)).thenReturn(vaga);
		Mockito.when(vagaRepository.existsByNumeroVaga(vaga.getNumeroVaga())).thenReturn(false);
		assertEquals(vaga, vagaService.atualizar(vaga));
	}

	@Test
	void testAtualizarException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(vagaRepository.findById(vaga.getId())).thenReturn(Optional.empty());
			vagaService.atualizar(vaga);
		});

		String mensagemEsperada = "Vaga não encontrada";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarPorId() {
		Mockito.when(vagaRepository.findById(vaga.getId())).thenReturn(resultado);
		assertEquals(resultado, vagaService.listarPorId(vaga.getId()));
	}

	@Test
	void testListarPorIdException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(vagaRepository.findById(vaga.getId())).thenReturn(Optional.empty());
			vagaService.listarPorId(vaga.getId());
		});

		String mensagemEsperada = "Vaga não encontrada";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarPorNumeroVaga() {
		Mockito.when(vagaRepository.findByNumeroVaga(vaga.getNumeroVaga())).thenReturn(vaga);
		assertEquals(vaga, vagaService.listarPorNumeroVaga(vaga.getNumeroVaga()));
	}

	@Test
	void testListarPorNumeroVagaException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(vagaRepository.findByNumeroVaga(vaga.getNumeroVaga())).thenReturn(null);
			assertEquals(vaga, vagaService.listarPorNumeroVaga(vaga.getNumeroVaga()));
		});

		String mensagemEsperada = "Vaga não encontrada";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testListarPorOcupacao() {
		lista.add(vaga);
		lista.add(vaga);
		lista.add(vaga);
		Mockito.when(vagaRepository.findByOcupada(vaga.isOcupada())).thenReturn(lista);
		assertEquals(lista, vagaService.listarPorOcupacao(vaga.isOcupada()));
	}

	@Test
	void testVerificaVaga() {
		Mockito.when(vagaRepository.existsByNumeroVaga(vaga.getNumeroVaga())).thenReturn(true);
		assertEquals(true, vagaService.verificaVaga(vaga.getNumeroVaga()));
	}

	@Test
	void testAtualizaEstadoVagaTrue() {
		Mockito.when(vagaRepository.save(vaga)).thenReturn(vaga);
		Mockito.when(vagaRepository.findById(vaga.getId())).thenReturn(resultado);
		assertEquals(vaga, vagaService.atualizaEstadoVaga(vaga.getId()));
	}

	@Test
	void testAtualizaEstadoVagaFalse() {
		vaga.setOcupada(true);
		Mockito.when(vagaRepository.save(vaga)).thenReturn(vaga);
		Mockito.when(vagaRepository.findById(vaga.getId())).thenReturn(resultado);
		assertEquals(vaga, vagaService.atualizaEstadoVaga(vaga.getId()));
	}

}
