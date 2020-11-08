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

import br.com.desafiobeca.model.Vaga;
import br.com.desafiobeca.service.impl.VagaServiceImpl;

class VagaControllerTest {

	private Vaga vaga;
	private List<Vaga> lista;
	private Optional<Vaga> resultado;

	@InjectMocks
	private VagaController vagaController;

	@Mock
	private VagaServiceImpl vagaService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		vaga = new Vaga();
		vaga.setId(1L);
		vaga.setNumeroVaga(1);
		vaga.setOcupada(false);

		lista = new ArrayList<>();
		lista.add(vaga);

		resultado = Optional.of(vaga);
	}

	@Test
	void testSalvar() {
		Mockito.when(vagaService.salvar(vaga.getNumeroVaga())).thenReturn(vaga);
		assertNotNull(vagaController.salvar(vaga.getNumeroVaga()));
	}

	@Test
	void testAtualizar() {
		Mockito.when(vagaService.atualizar(vaga)).thenReturn(vaga);
		assertNotNull(vagaController.atualizar(vaga));
	}

	@Test
	void testListarTodasVagas() {
		Mockito.when(vagaService.listarTodasVagas()).thenReturn(lista);
		assertNotNull(vagaController.listarTodasVagas());
	}

	@Test
	void testListarVagaPorId() {
		Mockito.when(vagaService.listarPorId(vaga.getId())).thenReturn(resultado);
		assertNotNull(vagaController.listarVagaPorId(vaga.getId()));
	}

	@Test
	void testListarVagaPorNumeroVaga() {
		Mockito.when(vagaService.listarPorNumeroVaga(vaga.getNumeroVaga())).thenReturn(vaga);
		assertNotNull(vagaController.listarVagaPorNumeroVaga(vaga.getNumeroVaga()));
	}

	@Test
	void testListarPorOcupacao() {
		Mockito.when(vagaService.listarPorOcupacao(vaga.isOcupada())).thenReturn(lista);
		assertNotNull(vagaController.listarPorOcupacao(vaga.isOcupada()));
	}

}
