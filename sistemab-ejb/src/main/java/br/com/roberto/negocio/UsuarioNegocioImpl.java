package br.com.roberto.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.roberto.dao.UsuarioDao;
import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;
import br.com.roberto.negocio.exception.DadosNaoEncontradosException;
import br.com.roberto.negocio.servico.UsuarioServico;

@Stateless
public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private UsuarioServico usuarioServico;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<UsuarioDto> listaTodos(int inicio, int tamanho) {
		List<Usuario> usuarios =  usuarioDao.buscaTodos(inicio, tamanho);
		
		if (usuarios==null){
			throw new DadosNaoEncontradosException("Usuarios Não Encontrados");
		}
		List<UsuarioDto> usuariosDTO = new ArrayList<>();
		
		usuarios.forEach(
				usuario -> usuariosDTO.add(new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getUsuario())));

		return usuariosDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public UsuarioDto listaPorId(Long id) {
		Usuario usuario = usuarioServico.pesquisaUsuario(id);
		return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getUsuario());
	}

	@Override
	public UsuarioDto adiciona(Usuario usuario) {
		Usuario usuarioRet = usuarioDao.salvar(usuario);
		return new UsuarioDto(usuarioRet.getId(), usuarioRet.getNome(), usuarioRet.getUsuario());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UsuarioDto atualiza(Long id, Usuario usuario) {
		Usuario usuarioRet = usuarioServico.atualiza(id,usuario);
		return new UsuarioDto(usuarioRet.getId(), usuarioRet.getNome(), usuarioRet.getUsuario());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean remove(Long id) {
		//try {
			usuarioServico.remove(id);
			return true;
		/*
		 * }catch (Exception e) { new RuntimeException(e.getCause()+" "+e.getMessage());
		 * } return false;
		 */
	}

	
	
}
