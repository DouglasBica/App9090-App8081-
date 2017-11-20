package space.indietech.produto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

	@Id
	@Column(name = "codigo")
	@GeneratedValue
	private Long codigo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "custo")
	private double custo;

	@Column(name = "perc_lucro")
	private double perc_lucro;

	@Column(name = "qtd")
	private int qtd;

	@Column(name = "data_compra")
	private Date data_compra;

	@Column(name = "ativo")
	private boolean ativo;

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPerc_lucro() {
		return perc_lucro;
	}

	public void setPerc_lucro(double perc_lucro) {
		this.perc_lucro = perc_lucro;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Date getData_compra() {
		return data_compra;
	}

	public void setData_compra(Date data_compra) {
		this.data_compra = data_compra;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
