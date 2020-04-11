package br.com.roberto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario", schema = "sistemab")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long codUsuario;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "usuario", nullable = false, length = 100)
	private String usuario;

	@Column(name = "senha", nullable = false, length = 20)
	private String senha;

	@Column(name = "ativo", nullable = false, length = 1)
	private boolean ativo = true;

	public Usuario() {

	}

	public Usuario(Long codUsuario, String nome, String usuario, String senha) {
		super();
		this.codUsuario = codUsuario;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Long getId() {
		return codUsuario;
	}

	public void setId(Long id) {
		this.codUsuario = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codUsuario == null) ? 0 : codUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codUsuario == null) {
			if (other.codUsuario != null)
				return false;
		} else if (!codUsuario.equals(other.codUsuario))
			return false;
		return true;
	}

}
