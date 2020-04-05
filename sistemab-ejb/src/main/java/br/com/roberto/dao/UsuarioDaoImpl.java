package br.com.roberto.dao;

import java.util.List;

import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;

public class UsuarioDaoImpl extends GenericoDao<Usuario, UsuarioDto> implements UsuarioDao {

	@Override
	public List<Usuario> buscaTodos(int inicio, int tamanho) {
		List<Usuario> usuarios = getManager().createQuery("select u from Usuario u", Usuario.class)
				.setFirstResult(inicio).setMaxResults(tamanho).getResultList();
		return usuarios;

	}

}