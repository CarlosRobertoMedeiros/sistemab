package br.com.roberto.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_perfil", schema = "sistemaexemplods")
public class Perfil {
	
	@Id
	private Long id;
	@Column(length = 100, nullable = false)
	private String nome;
	@Column(length = 100, nullable = true)
	private String descricao;
	
	@OneToMany
	List<Usuario> usuarios;

}
