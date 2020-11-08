package br.com.desafiobeca.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity(name = "tb_funcionario")
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends Pessoa {
	@NotNull(message = "Salario nao pode ser vazio")
	private Double salario;

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
}
