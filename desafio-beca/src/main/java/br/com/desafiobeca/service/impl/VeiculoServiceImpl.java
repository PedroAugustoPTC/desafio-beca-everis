package br.com.desafiobeca.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiobeca.model.Veiculo;
import br.com.desafiobeca.repository.VeiculoRepository;
import br.com.desafiobeca.service.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	VeiculoRepository veiculoRepository;

	@Autowired
	PessoaServiceImpl pessoaService;

	@Override
	public Veiculo salvar(Veiculo veiculo) {
		pessoaService.listarPorCpf(veiculo.getProprietario().getCpf());
		if (veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
			throw new EntityExistsException("Veículo já cadastrado");
		} else
			return veiculoRepository.save(veiculo);
	}

	@Override
	public List<Veiculo> listarTodosVeiculos() {
		return veiculoRepository.findAll();
	}

	@Override
	public Optional<Veiculo> listarPorId(Long id) {
		return veiculoRepository.findById(id);
	}

	@Override
	public Veiculo listarVeiculoPorPlaca(String placa) {
		Veiculo veiculo = veiculoRepository.findByPlaca(placa);
		if (veiculo != null) {
			return veiculo;
		} else {
			throw new NullPointerException("Veículo não encontrado");
		}
	}

	@Override
	public Veiculo atualizar(Veiculo veiculo) {
		return salvar(veiculo);
	}
}
