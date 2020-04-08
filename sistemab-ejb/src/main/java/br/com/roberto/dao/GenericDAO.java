package br.com.roberto.dao;

public interface GenericDAO<T,Long> {
	
	T buscaPorId(Long id);
	T salvar(T entidade);
	boolean remove(Long id);
	
	
	

}
