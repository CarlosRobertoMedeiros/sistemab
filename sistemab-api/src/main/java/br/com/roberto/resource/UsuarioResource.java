package br.com.roberto.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
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

import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;
import br.com.roberto.negocio.UsuarioNegocio;
import br.com.roberto.resource.bean.UsuarioFilterBean;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class UsuarioResource {
		
	@Inject
	private UsuarioNegocio usuarioNegocio;

	
	@PermitAll
	@GET
	public Response getUsuarios(@BeanParam UsuarioFilterBean filterBean)  {
		List<UsuarioDto> usuarios = usuarioNegocio.listaTodos(filterBean.getInicio(), filterBean.getTamanho());
		
		return Response
				.ok(usuarios)
				.build();
	}

	@PermitAll
	@GET
	@Path("/{id}")
	public Response getUsuario(@PathParam("id") Long id) {
		UsuarioDto usuario = usuarioNegocio.listaPorId(id);
		
		return Response
				.ok(usuario)
				.build();
	}

	@RolesAllowed("ADMIN")
	@POST
	public Response adicionarUsuario(Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {

		UsuarioDto novoUsuario = usuarioNegocio.adiciona(usuario);
		String newId = String.valueOf(novoUsuario.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
				 .status(Status.CREATED)
				 .entity(novoUsuario)
				 .build();
	}
	
	@RolesAllowed("ADMIN")
	@PUT
	@Path("/{id}")
	public Response atualizarUsuario(@PathParam("id") Long id,  Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {
		
		UsuarioDto meuUsuario = usuarioNegocio.atualiza(id, usuario);
		URI uri = uriInfo.getAbsolutePathBuilder().path(meuUsuario.getId().toString()).build();
		
		return Response.created(uri)
				 .status(Status.OK)
				 .entity(meuUsuario)
				 .build();
	}
	
	@RolesAllowed("ADMIN")
	@DELETE
	@Path("/{id}")
	public Response excluirUsuario(@PathParam("id") Long id) {
		usuarioNegocio.remove(id);
		return Response
			 .status(Status.OK)
			 .build();

	}
}
