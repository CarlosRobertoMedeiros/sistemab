package br.com.roberto.infraestrutura.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import br.com.roberto.negocio.exception.ExceptionMessage;

//@Provider
//@Priority(3)
public class GenericoExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
				
		ExceptionMessage em = new ExceptionMessage("500",exception.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(em)
				.build();
	}

}
