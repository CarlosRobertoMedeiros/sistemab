package br.com.roberto.dao;

import java.lang.reflect.ParameterizedType;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Local
public abstract class GenericoDao<T, K> {

	private Class<T> classe;
	
	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public T buscaPorId(int id, T t) {
		return (T) manager.find(getClasse(), id);
	}
	
	public T salvar(T t) {
		manager.merge(t);
		return t;
	}
	
	public boolean excluir(int id) {
		Long idInterno = new Long(id);
		T objetoExistente = (T) manager.find(getClasse(), idInterno);
		if (objetoExistente == null) {
			return false;
		}
		manager.remove(objetoExistente);
		return true;
	}
	
	private Class getClasse() {
		if(classe == null) {
			classe = (Class<T>) ((ParameterizedType) getClasse().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return classe;
	}

	public EntityManager getManager() {
		return manager;
	}
}
