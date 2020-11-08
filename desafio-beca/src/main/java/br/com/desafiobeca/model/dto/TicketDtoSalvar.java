package br.com.desafiobeca.model.dto;

public class TicketDtoSalvar {
	private String placa;
	private Integer numeroVaga;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getNumeroVaga() {
		return numeroVaga;
	}

	public void setNumeroVaga(Integer numeroVaga) {
		this.numeroVaga = numeroVaga;
	}

	@Override
	public String toString() {
		return "TicketDto [placa=" + placa + ", numeroVaga=" + numeroVaga + "]";
	}
}
