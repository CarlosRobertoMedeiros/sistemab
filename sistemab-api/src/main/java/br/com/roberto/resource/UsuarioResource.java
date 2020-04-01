package br.com.roberto.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import br.com.roberto.business.UsuarioBusiness;
import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;
import br.com.roberto.resource.bean.UsuarioFilterBean;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class UsuarioResource {
		
	@Inject
	private UsuarioBusiness usuarioBusiness;


	@PermitAll
	@GET
	public Response getUsuarios(@BeanParam UsuarioFilterBean filterBean)  {
		List<UsuarioDto> usuarios = usuarioBusiness.listaTodos(filterBean.getInicio(), filterBean.getTamanho());
		
		if (usuarios.isEmpty()) {
			return Response
				.noContent()
				.build();
		}
		return Response
				.ok(usuarios)
				.build();
	}

	@PermitAll
	@GET
	@Path("/{id}")
	public Response getUsuario(@PathParam("id") int id) {
		UsuarioDto usuario = usuarioBusiness.listaPorId(id);
		if(usuario==null) {
			return Response
					.noContent()
					.build();
		}
		return Response
				.ok(usuario)
				.build();
	}

	@RolesAllowed("ADMIN")
	@POST
	public Response adicionarUsuario(Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {

		UsuarioDto novoUsuario = usuarioBusiness.adiciona(usuario);
		String newId = String.valueOf(novoUsuario.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
				 .status(Status.CREATED)
				 .entity(novoUsuario)
				 .build();
	}
	
	@PUT
	@Path("/{id}")
	public Response atualizarUsuario(@PathParam("id") int id,  Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {
		
		UsuarioDto meuUsuario = usuarioBusiness.atualiza(id, usuario);
		URI uri = uriInfo.getAbsolutePathBuilder().path(meuUsuario.getId().toString()).build();
		
		return Response.created(uri)
				 .status(Status.OK)
				 .entity(meuUsuario)
				 .build();
	}
	
	@RolesAllowed("ADMIN")
	@DELETE
	@Path("/{id}")
	public Response excluirUsuario(@PathParam("id") int id) {
		boolean excluiu = usuarioBusiness.remove(id);
		if (excluiu) {
			return Response
					 .status(Status.OK)
					 .build();
		}
		else {
			return Response
					.status(Status.NOT_FOUND)
					.build();
		}
	}
	
	

}
