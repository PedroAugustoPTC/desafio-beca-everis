package br.com.desafiobeca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity(name = "tb_veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Placa não pode ser nula. Caso o veículo nao seja emplacado, atribua uma placa aleatória "
			+ "de identificação com apenas letras")
	@Pattern(regexp = "([A-Z]{3}[-]([A-Z0-9]){4})|([A-Z]{3}[A-Z0-9]{1}[A-Z]{1}[A-Z0-9]{2})", message = "Formato de placa inválido. Exemplo de formato "
			+ "válido: XXX-0000, XXX-XXXX, XXX0X00, XXXXXXX")
	private String placa;

	@ManyToOne
	private Pessoa proprietario;

	public Pessoa getProprietario() {
		return proprietario;
	}

	public void setProprietario(Pessoa proprietario) {
		this.proprietario = proprietario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

//	@Override
//	public boolean equals(Object veiculo) {
//		if (this.placa.equals(((Veiculo) veiculo).getPlaca())) {
//			return true;
//		} else {
//			return false;
//		}
//	}

}
