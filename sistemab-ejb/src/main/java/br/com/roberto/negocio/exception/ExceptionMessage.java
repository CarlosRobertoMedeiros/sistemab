package br.com.roberto.negocio.exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExceptionMessage {

	private String mensagemErro;
	private String codigoErro;

	public ExceptionMessage() {
	}

	public ExceptionMessage(String codigoErro, String MensagemErro) {
		super();
		this.codigoErro = codigoErro;
		this.mensagemErro = MensagemErro;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(String codigoErro) {
		this.codigoErro = codigoErro;
	}

}
