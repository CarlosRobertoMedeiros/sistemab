package br.com.roberto.dao;

import java.util.List;

import br.com.roberto.model.Usuario;

public class UsuarioDao extends GenericoDao<Usuario, Long>{

	public List<Usuario> buscaTodos(int inicio, int tamanho) {
		List<Usuario> usuarios = this.getManager().createQuery("select u from Usuario u", Usuario.class)
				.setFirstResult(inicio).setMaxResults(tamanho).getResultList();
		return usuarios;

	}

	

}