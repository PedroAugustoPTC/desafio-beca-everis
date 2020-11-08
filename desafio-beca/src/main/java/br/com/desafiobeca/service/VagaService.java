package br.com.desafiobeca.service;

import java.util.List;

import br.com.desafiobeca.model.Vaga;

public interface VagaService {
	public Vaga salvar(Integer numeroVaga);

	public List<Vaga> listarTodasVagas();

	public Vaga atualizar(Vaga vaga);

	public Vaga listarPorId(Long id);

	public Vaga listarPorNumeroVaga(Integer numeroVaga);

	public boolean verificaVaga(Integer numeroVaga);

	public List<Vaga> listarPorOcupacao(boolean ocupado);
}
