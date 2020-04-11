package br.com.roberto.resource;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
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
import br.com.roberto.hateos.MontaMensagemHateos;
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

	@Inject
	private MontaMensagemHateos mensagemHateos;

	@GET
	public Response getUsuarios(@BeanParam UsuarioFilterBean filterBean, @Context UriInfo uriInfo)  {
		List<UsuarioDto> usuarios = usuarioNegocio.listaTodos(filterBean.getInicio(), filterBean.getTamanho());
		List<UsuarioDto> usuariosTratados = new ArrayList<>();
		
		usuarios.forEach(usuario ->{
			mensagemHateos.acoesHateosParaUsuario(uriInfo,usuario);
			usuariosTratados.add(usuario);
		});
		
		
		return Response
				.ok(usuariosTratados)
				.build();
	}

	@GET
	@Path("/{id}")
	public Response getUsuario(@PathParam("id") Long id, @Context UriInfo uriInfo) {
		try {
			UsuarioDto usuario = usuarioNegocio.listaPorId(id);
			mensagemHateos.acoesHateosParaEdicaoUsuario(uriInfo,usuario);

			return Response
					.ok(usuario)
					.build();

		}catch (EJBException exception){
			System.out.println("Qual o Problema => "+exception.getCause()+" "+exception.getMessage());
		}
		return  null;
		

	}

	@POST
	public Response adicionarUsuario(Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {

		UsuarioDto novoUsuario = usuarioNegocio.adiciona(usuario);
		
		mensagemHateos.acoesHateosParaInsercao(uriInfo,novoUsuario);
		
		return Response
				 .status(Status.CREATED)
				 .entity(novoUsuario)
				 .build();
	}
	
	@PUT
	@Path("/{id}")
	public Response atualizarUsuario(@PathParam("id") Long id,  Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {
		
		UsuarioDto usuarioAtualizado = usuarioNegocio.atualiza(id, usuario);
		
		mensagemHateos.acoesHateosParaEdicaoUsuario(uriInfo,usuarioAtualizado);

		return Response
				 .status(Status.CREATED)
				 .entity(usuarioAtualizado)
				 .build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response excluirUsuario(@PathParam("id") Long id,  @Context UriInfo uriInfo) {
		usuarioNegocio.remove(id);
		UsuarioDto usuario = new UsuarioDto();
		
		mensagemHateos.acoesHateosParaExcluir(uriInfo, usuario);
				
		return Response
			 .status(Status.OK)
			 .entity(usuario)
			 .build();

	}
	
}
