package br.com.roberto.business;

import java.util.List;

import javax.inject.Inject;

import br.com.roberto.dao.UsuarioDao;
import br.com.roberto.dao.UsuarioDaoImpl;
import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;

public class UsuarioBusinessImpl implements UsuarioBusiness {

	@Inject
	private UsuarioDaoImpl usuarioDao;
	
	@Override
	public List<UsuarioDto> listaTodos(int inicio, int tamanho) {
		return usuarioDao.listaTodos(inicio, tamanho);
	}

	@Override
	public UsuarioDto listaPorId(int id) {
		return usuarioDao.listaPorId(id);
	}

	@Override
	public UsuarioDto adiciona(Usuario usuario) {
		UsuarioDto novoUsuario = usuarioDao.adiciona(usuario);
		return novoUsuario;
	}

	@Override
	public UsuarioDto atualiza(int id, Usuario usuario) {
		UsuarioDto usuarioAtualizado = usuarioDao.atualiza(id, usuario);
		return usuarioAtualizado;
	}

	@Override
	public boolean remove(int id) {
		return usuarioDao.remove(id);
	}

}
