package br.com.desafiobeca.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import br.com.desafiobeca.model.Veiculo;
import br.com.desafiobeca.repository.VeiculoRepository;

class VeiculoServiceImplTest {

	private Veiculo veiculo;
	private Pessoa proprietario;
	private List<Veiculo> lista;
	private Optional<Veiculo> resultado;

	@InjectMocks
	private VeiculoServiceImpl veiculoService;

	@Mock
	private VeiculoRepository veiculoRepository;

	@Mock
	private PessoaServiceImpl pessoaService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		veiculo = new Veiculo();
		proprietario = new Pessoa();
		proprietario.setId(1L);
		proprietario.setNome("Pedro");
		proprietario.setCpf("143.313.476-48");
		proprietario.setEmail("sddsadas@dasdas");
		proprietario.setTelefone("(034)999506807");

		veiculo.setId(1L);
		veiculo.setPlaca("WER-3456");
		veiculo.setProprietario(proprietario);

	}

	@Test
	void testSalvar() {
		Mockito.when(pessoaService.listarPorCpf(proprietario.getCpf())).thenReturn(proprietario);
		Mockito.when(veiculoRepository.existsByPlaca(veiculo.getPlaca())).thenReturn(false);
		Mockito.when(veiculoRepository.save(veiculo)).thenReturn(veiculo);
		assertEquals(veiculo, veiculoService.salvar(veiculo));
	}

	@Test
	void testSalvarEntityExistsException() {
		Exception exception = assertThrows(EntityExistsException.class, () -> {
			Mockito.when(pessoaService.listarPorCpf(proprietario.getCpf())).thenReturn(proprietario);
			Mockito.when(veiculoRepository.existsByPlaca(veiculo.getPlaca())).thenReturn(true);
			Mockito.when(veiculoRepository.save(veiculo)).thenReturn(veiculo);
			assertEquals(veiculo, veiculoService.salvar(veiculo));
		});

		String mensagemEsperada = "Veículo já cadastrado";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));

	}

	@Test
	void testListarTodosVeiculos() {
		lista = new ArrayList<>();
		lista.add(veiculo);
		lista.add(veiculo);
		lista.add(veiculo);
		Mockito.when(veiculoRepository.findAll()).thenReturn(lista);
		assertEquals(lista, veiculoService.listarTodosVeiculos());
	}

	@Test
	void testListarPorId() {
		resultado = Optional.of(veiculo);
		Mockito.when(veiculoRepository.findById(veiculo.getId())).thenReturn(resultado);
		assertEquals(veiculo, veiculoService.listarPorId(veiculo.getId()).get());
	}

	@Test
	void testListarVeiculoPorPlaca() {
		Mockito.when(veiculoRepository.findByPlaca(veiculo.getPlaca())).thenReturn(veiculo);
		assertEquals(veiculo, veiculoService.listarVeiculoPorPlaca(veiculo.getPlaca()));
	}

	@Test
	void testListarVeiculoPorPlacaException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			Mockito.when(veiculoRepository.findByPlaca(veiculo.getPlaca())).thenReturn(null);
			assertEquals(veiculo, veiculoService.listarVeiculoPorPlaca(veiculo.getPlaca()));
		});

		String mensagemEsperada = "Veículo não encontrado";
		String mensagemAtual = exception.getMessage();

		assertTrue(mensagemAtual.contains(mensagemEsperada));
	}

	@Test
	void testAtualizar() {
		Mockito.when(pessoaService.listarPorCpf(veiculo.getProprietario().getCpf())).thenReturn(proprietario);
		Mockito.when(veiculoRepository.existsByPlaca(veiculo.getPlaca())).thenReturn(false);
		Mockito.when(veiculoRepository.save(veiculo)).thenReturn(veiculo);
		assertEquals(veiculo, veiculoService.atualizar(veiculo));
	}

}
