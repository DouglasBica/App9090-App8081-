package space.indietech.cliente;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = -5514056950150921964L;

	@Id
	protected String cpfCnpj;

	@NotNull(message = "Nome deve ser preenchido")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres")
	private String nome;

	@Length(min = 7, max = 16, message = "Nome deve conter entre 7 e 16 caracteres")
	private String telefone;

	@Email
	private String email;

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
