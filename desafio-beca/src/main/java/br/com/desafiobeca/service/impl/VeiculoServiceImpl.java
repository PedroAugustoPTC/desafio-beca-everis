package br.com.desafiobeca.service.impl;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiobeca.model.Pessoa;
import br.com.desafiobeca.model.Veiculo;
import br.com.desafiobeca.repository.VeiculoRepository;
import br.com.desafiobeca.service.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	VeiculoRepository veiculoRepository;

	@Autowired
	PessoaServiceImpl pessoaService;

	public Veiculo salvar(Veiculo veiculo) {
		Pessoa pessoa = pessoaService.listarPorCpf(veiculo.getProprietario().getCpf());
		if (veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
			throw new EntityExistsException("Veículo já cadastrado");
		} else if (!(pessoa.equals(veiculo.getProprietario()))) {
			throw new NullPointerException("Proprietário não cadastrado");
		} else {
			return veiculoRepository.save(veiculo);
		}

	}

	public List<Veiculo> listarTodosVeiculos() {
		return veiculoRepository.findAll();
	}

	public Veiculo listarPorId(Long id) {
		return veiculoRepository.findById(id).get();
	}

	public Veiculo listarVeiculoPorPlaca(String placa) {
		Veiculo veiculo = veiculoRepository.findByPlaca(placa);
		if (veiculo != null) {
			return veiculo;
		} else {
			throw new NullPointerException("Veículo não encontrado");
		}
	}

	public Veiculo atualizar(Veiculo veiculo) {
		return salvar(veiculo);
	}
}
