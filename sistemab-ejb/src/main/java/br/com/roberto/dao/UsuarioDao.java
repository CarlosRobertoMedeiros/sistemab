package br.com.roberto.dao;

import java.util.List;

import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;

public class UsuarioDao extends CrudGenerico<Usuario, UsuarioDto> {

	@Override
	public Usuario criar(Usuario t) {
		return super.criar(t);
	}

	@Override
	public boolean excluir(int id) {
		return super.excluir(id);
	}

	@Override
	public Usuario buscaPorId(int id, Usuario usuario) {
		return super.buscaPorId(id, usuario);
	}

	@Override
	public Usuario atualiza(int id, Usuario t) {
		return super.atualiza(id, t);
	}

	public List<Usuario> buscaTodos(int inicio, int tamanho) {
		List<Usuario> usuarios = getManager().createQuery("select u from Usuario u", Usuario.class)
				.setFirstResult(inicio).setMaxResults(tamanho).getResultList();
		return usuarios;

	}

}