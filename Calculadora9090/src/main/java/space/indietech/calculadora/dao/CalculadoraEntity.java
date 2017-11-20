package space.indietech.calculadora.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calculo")
public class CalculadoraEntity {

	@Id
	@Column(name = "expressao")
	private String expressao;
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "criado_em")
	private Date criado_em;

	public String getExpressao() {
		return expressao;
	}

	public void setExpressao(String expressao) {
		this.expressao = expressao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getCriado_em() {
		return criado_em;
	}

	public void setCriado_em(Date criado_em) {
		this.criado_em = criado_em;
	}
	
	
	
	
}
