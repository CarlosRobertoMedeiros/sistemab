package br.com.roberto.dao;

import java.util.List;

import br.com.roberto.model.Usuario;

public interface UsuarioDao {

	List<Usuario> buscaTodos(int inicio, int tamanho);
}
