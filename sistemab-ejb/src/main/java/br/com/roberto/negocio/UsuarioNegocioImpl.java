package br.com.roberto.negocio;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import br.com.roberto.dao.UsuarioDaoImpl;
import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;
import br.com.roberto.negocio.servico.ServicoUsuario;

@Stateless
public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Inject
	private UsuarioDaoImpl usuarioDao;
	
	@Inject
	private ServicoUsuario servicoUsuario;
		
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<UsuarioDto> listaTodos(int inicio, int tamanho) {
		List<Usuario> usuarios =  usuarioDao.buscaTodos(inicio, tamanho);
		List<UsuarioDto> usuariosDTO = new ArrayList<>();
		
		usuarios.forEach(
				usuario -> usuariosDTO.add(new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getUsuario())));

		return usuariosDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public UsuarioDto listaPorId(int id) {
		Usuario usuario =  usuarioDao.buscaPorId(id, new Usuario());
		return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getUsuario());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UsuarioDto adiciona(Usuario usuario) {
		Usuario usuarioRet = usuarioDao.salvar(usuario);
		return new UsuarioDto(usuarioRet.getId(), usuarioRet.getNome(), usuarioRet.getUsuario());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UsuarioDto atualiza(int id, Usuario usuario) {
		Usuario usuarioParaSalvar = servicoUsuario.pesquisaUsuario(id);
		Usuario usuarioRet =  usuarioDao.salvar(usuarioParaSalvar);
		return new UsuarioDto(usuarioRet.getId(), usuarioRet.getNome(), usuarioRet.getUsuario());
	}

	

	@Override	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean remove(int id) {
		return usuarioDao.excluir(id);
	}

}
