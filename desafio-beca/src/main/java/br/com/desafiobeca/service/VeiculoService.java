package br.com.desafiobeca.service;

import java.util.List;
import java.util.Optional;

import br.com.desafiobeca.model.Veiculo;

public interface VeiculoService {
	public Veiculo salvar(Veiculo veiculo);

	public List<Veiculo> listarTodosVeiculos();

	public Optional<Veiculo> listarPorId(Long id);

	public Veiculo listarVeiculoPorPlaca(String placa);

	public Veiculo atualizar(Veiculo veiculo);
}
