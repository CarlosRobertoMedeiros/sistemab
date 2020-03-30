package br.com.roberto.dto;

public class UsuarioDto {
	private Long id;
	private String nome;
	private String usuario;

	public UsuarioDto(Long id, String nome, String usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
