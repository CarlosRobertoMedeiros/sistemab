package br.com.roberto.dao;

import java.lang.reflect.ParameterizedType;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Local
public class GenericoDao<T, Long> implements GenericDAO<T, Long>  {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	private Class<T> classEntidade;
	
	
	@SuppressWarnings("unchecked")
	public GenericoDao() {
		this.classEntidade = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]; 
	}
	
	@Override
	public T buscaPorId(Long id) {
		return manager.find(classEntidade, id);
	}

	@Override
	public T salvar(T entidade) {
	    T meuObjeto = manager.merge(entidade);
		return meuObjeto;
	}
	
	@Override
	public boolean remove(Long id) {
		try {
			T minhaEntidade = this.buscaPorId(id);
			manager.remove(minhaEntidade);
			return true;
		}catch (Exception e) {
			throw new RuntimeException(e.getCause()+" "+e.getMessage());
		}
	}
	
	public EntityManager getManager() {
		return manager;
	}

	

	
}
