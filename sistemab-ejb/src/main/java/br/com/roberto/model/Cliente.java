package br.com.roberto.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "tb_cliente", schema = "sistemaexemplods")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 15, nullable = false)
	private Sexo sexo;
	
	@Column(length = 100, nullable = false)
	private String email;
	
	@Embedded
	private Endereco endereco;
}
