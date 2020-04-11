package br.com.roberto.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

	@GET
	public Response getUsuarios(@BeanParam UsuarioFilterBean filterBean, @Context UriInfo uriInfo)  {
		List<UsuarioDto> usuarios = usuarioNegocio.listaTodos(filterBean.getInicio(), filterBean.getTamanho());
		List<UsuarioDto> usuariosTratados = new ArrayList<>();
		
		usuarios.forEach(usuario ->{
			acoesHateosParaUsuario(uriInfo,usuario);
			usuariosTratados.add(usuario);
		});
		
		
		return Response
				.ok(usuariosTratados)
				.build();
	}

	@GET
	@Path("/{id}")
	public Response getUsuario(@PathParam("id") Long id, @Context UriInfo uriInfo) {
		UsuarioDto usuario = usuarioNegocio.listaPorId(id);
		
		acoesHateosParaUsuario(uriInfo, usuario);
		
		return Response
				.ok(usuario)
				.build();
	}

	@POST
	public Response adicionarUsuario(Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {

		UsuarioDto novoUsuario = usuarioNegocio.adiciona(usuario);
		
		acoesHateosParaUsuario(uriInfo, novoUsuario);
		
		return Response
				 .status(Status.CREATED)
				 .entity(novoUsuario)
				 .build();
	}
	
	@PUT
	@Path("/{id}")
	public Response atualizarUsuario(@PathParam("id") Long id,  Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {
		
		UsuarioDto meuUsuario = usuarioNegocio.atualiza(id, usuario);
		
		acoesHateosParaUsuario(uriInfo, meuUsuario);

		return Response
				 .status(Status.CREATED)
				 .entity(meuUsuario)
				 .build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response excluirUsuario(@PathParam("id") Long id,  @Context UriInfo uriInfo) {
		usuarioNegocio.remove(id);
		UsuarioDto usuario = new UsuarioDto();
		acoesHateosParaExcluir(uriInfo, usuario);
		
		return Response
			 .status(Status.OK)
			 .entity(usuario)
			 .build();

	}

	
	private String getUriForSelf(UriInfo uriInfo, UsuarioDto usuario) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(UsuarioResource.class)
				.path(Long.toString(usuario.getId()))
				.build()
				.toString();
		return uri;
	}
	
	private String getUriForAdicionar(UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(UsuarioResource.class)
				.build()
				.toString();
		return uri;
	}
	
	private String getUriForExcluir(UriInfo uriInfo, UsuarioDto usuario) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(UsuarioResource.class)
				.path(Long.toString(usuario.getId()))
				.build()
				.toString();
		return uri;
	}
	
	private String getUriForAlterar(UriInfo uriInfo, UsuarioDto usuario) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(UsuarioResource.class)
				.path(Long.toString(usuario.getId()))
				.build()
				.toString();
		return uri;
	}
	
	private String getUriForListarTodos(UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(UsuarioResource.class)
				.build()
				.toString()+"?inicio=0&tamanho=10";
		return uri;
	}
	
	private UsuarioDto acoesHateosParaUsuario(UriInfo uriInfo, UsuarioDto usuario) {
		usuario.addLink(getUriForSelf(uriInfo, usuario),"listarPorId");
		usuario.addLink(getUriForAdicionar(uriInfo),"adicionar");
		usuario.addLink(getUriForExcluir(uriInfo, usuario),"excluir");
		usuario.addLink(getUriForAlterar(uriInfo, usuario),"alterar");
		usuario.addLink(getUriForListarTodos(uriInfo),"listarTodos","Atenção a Paginação");
		return usuario; 
	}
	
	private UsuarioDto acoesHateosParaExcluir(UriInfo uriInfo, UsuarioDto usuario) {
		usuario.addLink(getUriForAdicionar(uriInfo),"adicionar");
		usuario.addLink(getUriForListarTodos(uriInfo),"listarTodos","Atenção a Paginação");
		return usuario; 
	}
	
}
