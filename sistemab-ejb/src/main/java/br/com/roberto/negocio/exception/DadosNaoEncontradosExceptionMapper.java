package br.com.roberto.negocio.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DadosNaoEncontradosExceptionMapper implements ExceptionMapper<DadosNaoEncontradosException> {

	@Override
	public Response toResponse(DadosNaoEncontradosException exception) {
		
		ExceptionMessage em = new ExceptionMessage("404",exception.getMessage());
		return Response.status(Status.NOT_FOUND)
				.entity(em)
				.build();
	}

}
