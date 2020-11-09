package br.com.desafiobeca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "tb_vaga")
public class Vaga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Número da vaga não pode ser nulo")
	private Integer numeroVaga;

	private boolean ocupada = false;

	public Integer getNumeroVaga() {
		return numeroVaga;
	}

	public void setNumeroVaga(Integer numeroVaga) {
		this.numeroVaga = numeroVaga;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroVaga == null) ? 0 : numeroVaga.hashCode());
		result = prime * result + (ocupada ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Vaga vaga = (Vaga) obj;
		if ((id == vaga.getId()) && (numeroVaga == vaga.getNumeroVaga())) {
			return true;
		} else {
			return false;
		}
	}
}
