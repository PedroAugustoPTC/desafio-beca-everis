package br.com.desafiobeca.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.desafiobeca.model.Veiculo;
import br.com.desafiobeca.service.impl.VeiculoServiceImpl;

class VeiculoControllerTest {

	private Veiculo veiculo;

	@InjectMocks
	private VeiculoController veiculoController;

	@Mock
	private VeiculoServiceImpl veiculoServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		veiculo = new Veiculo();
		veiculo.setId(1L);
		veiculo.setPlaca("WER-3456");
		veiculo.setProprietario(null);

	}

	@Test
	void testSalvar() {
		Mockito.when(veiculoServiceImpl.salvar(veiculo)).thenReturn(veiculo);
		assertNotNull(veiculoController.salvar(veiculo));
	}

	@Test
	void testAtualizar() {
		Mockito.when(veiculoServiceImpl.atualizar(veiculo)).thenReturn(veiculo);
		assertNotNull(veiculoController.atualizar(veiculo));
	}

	@Test
	void testListarTodosVeiculos() {
		List<Veiculo> lista = new ArrayList<>();
		Mockito.when(veiculoServiceImpl.listarTodosVeiculos()).thenReturn(lista);
		assertNotNull(veiculoController.listarTodosVeiculos());
	}

	@Test
	void testListarVeiculoPorIdLong() {
		Mockito.when(veiculoServiceImpl.listarPorId(veiculo.getId())).thenReturn(veiculo);
		assertNotNull(veiculoController.listarVeiculoPorId(veiculo.getId()));
	}

	@Test
	void testListarVeiculoPorPlacaString() {
		Mockito.when(veiculoServiceImpl.listarVeiculoPorPlaca(veiculo.getPlaca())).thenReturn(veiculo);
		assertNotNull(veiculoController.listarVeiculoPorPlaca(veiculo.getPlaca()));
	}

}
