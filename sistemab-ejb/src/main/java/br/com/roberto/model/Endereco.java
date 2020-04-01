package br.com.roberto.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//TODO: https://viacep.com.br/

@Embeddable
public class Endereco {
	
	@Column(length = 20, nullable = false)
	private String cep;
	@Column(length = 100, nullable = false)
	private String logradouro;
	@Column(length = 100, nullable = false)
	private String complemento;
	@Column(length = 100, nullable = false)
	private String bairro;
	@Column(length = 100, nullable = false)
    private String localidade;
	@Column(length = 2, nullable = false)
    private String uf;
	@Column(length = 100, nullable = false)
	private String unidade;
}
