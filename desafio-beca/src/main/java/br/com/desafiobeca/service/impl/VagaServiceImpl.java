package br.com.desafiobeca.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiobeca.exceptions.AtualizarVagaException;
import br.com.desafiobeca.model.Vaga;
import br.com.desafiobeca.repository.VagaRepository;
import br.com.desafiobeca.service.VagaService;

@Service
public class VagaServiceImpl implements VagaService {

	@Autowired
	VagaRepository vagaRepository;

	@Override
	public Vaga salvar(Integer numeroVaga) {
		if (verificaVaga(numeroVaga)) {
			throw new EntityExistsException("Esse número de vaga já existe");
		} else {
			Vaga vaga = new Vaga();
			vaga.setNumeroVaga(numeroVaga);
			vaga.setOcupada(false);
			vagaRepository.save(vaga);
			return vaga;
		}
	}

	@Override
	public List<Vaga> listarTodasVagas() {
		return vagaRepository.findAll();
	}

	@Override
	public Vaga atualizar(Vaga vaga) {
		if (listarPorId(vaga.getId()).isPresent() && !((listarPorId(vaga.getId()).get()).isOcupada())
				&& !(verificaVaga(vaga.getNumeroVaga()))) {
			return vagaRepository.save(vaga);
		} else {
			throw new AtualizarVagaException("Verifique se essa vaga existe ou se não está ocupada ");
		}
	}

	@Override
	public Optional<Vaga> listarPorId(Long id) {
		Optional<Vaga> vaga = vagaRepository.findById(id);
		if (vaga.isPresent()) {
			return vaga;
		} else {
			throw new NullPointerException("Vaga não encontrada");
		}
	}

	@Override
	public Vaga listarPorNumeroVaga(Integer numeroVaga) {
		Vaga vaga = vagaRepository.findByNumeroVaga(numeroVaga);
		if (vaga != null) {
			return vaga;
		} else {
			throw new NullPointerException("Vaga não encontrada");
		}
	}

	@Override
	public List<Vaga> listarPorOcupacao(boolean ocupado) {
		return vagaRepository.findByOcupada(ocupado);
	}

	@Override
	public boolean verificaVaga(Integer numeroVaga) {
		return vagaRepository.existsByNumeroVaga(numeroVaga);
	}

	@Override
	public Vaga atualizaEstadoVaga(Long id) {
		Vaga vaga = listarPorId(id).get();

		if (vaga.isOcupada()) {
			vaga.setOcupada(false);
		} else {
			vaga.setOcupada(true);
		}

		return vagaRepository.save(vaga);
	}
}
