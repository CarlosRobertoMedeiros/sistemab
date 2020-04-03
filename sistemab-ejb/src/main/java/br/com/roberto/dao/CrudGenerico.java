package br.com.roberto.dao;

import java.lang.reflect.ParameterizedType;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Local
public abstract class CrudGenerico<T, K> {

	private Class<T> classe;
	
	@PersistenceContext
	private EntityManager manager;
	
	public T buscaPorId(int id, T t) {
		return manager.find((Class<T>) t, id);
	}
	
	
	public T criar(T t) {
		manager.persist(t);
		return t;
	}
	
	//TODO: Tem que Corrigir Essa Implementação
	public T atualiza(int id, T t) {
		Long idInterno = new Long(id);
		T objetoExistente = (T) manager.find(getClasse(), idInterno);
		if (objetoExistente == null) {
			return null;	
		}
		return manager.merge(t);
		
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
