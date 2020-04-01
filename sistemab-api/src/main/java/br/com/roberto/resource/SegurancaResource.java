package br.com.roberto.resource;

import javax.ws.rs.Produces;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/seguranca")
@Produces(MediaType.APPLICATION_JSON)
public class SegurancaResource {
	
	@RolesAllowed("ADMIN")
	@GET
	@Path("/mensagem")
	public String metodoSeguro() {
		return "Essa Api necessita de login !!!";
	} 
	
	
	
	

}
