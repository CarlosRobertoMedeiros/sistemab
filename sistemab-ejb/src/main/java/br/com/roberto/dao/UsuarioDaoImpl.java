package br.com.roberto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<UsuarioDto> listaTodos(int inicio, int tamanho) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u", Usuario.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();

		List<UsuarioDto> usuariosDTO = new ArrayList<>();

		usuarios.forEach(
				usuario -> usuariosDTO.add(new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getUsuario())));

		return usuariosDTO;
	}

	@Override
	public UsuarioDto listaPorId(int id) {
		Long idInterno = new Long(id);
		
		try {
			Usuario usuario = manager.createQuery("select u from Usuario u where u.id=:idInterno", Usuario.class)
				.setParameter("idInterno", idInterno)
				.getSingleResult();

			return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getUsuario());
		}catch (RuntimeException e) {
			UsuarioDto usuarioDto = null;
			return usuarioDto;
		}
	}

	@Override
	public UsuarioDto adiciona(Usuario usuario) {
		Usuario novoUsuario = manager.merge(usuario);
		return new UsuarioDto(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getUsuario());
	}

	@Override
	public UsuarioDto atualiza(int id, Usuario usuario) {
		Long idInterno = new Long(id);
		Usuario usuarioExistente = manager.find(Usuario.class, idInterno);
		
		if (usuarioExistente==null) {
			UsuarioDto usuarioDTO = null;
			return usuarioDTO;
		} 
		
		usuarioExistente.setNome(usuario.getNome());
		usuarioExistente.setUsuario(usuario.getUsuario());
		usuarioExistente.setSenha(usuario.getSenha());
		usuarioExistente = manager.merge(usuarioExistente);
		return new UsuarioDto(usuarioExistente.getId(), usuarioExistente.getNome(), usuarioExistente.getUsuario());
	}

	@Override
	public boolean remove(int id) {
		Long idInterno = new Long(id);
		Usuario usuarioExistente = manager.find(Usuario.class, idInterno);
		if (usuarioExistente==null) {
			return false;
		}
		manager.remove(usuarioExistente);
		return true;
	}

}
