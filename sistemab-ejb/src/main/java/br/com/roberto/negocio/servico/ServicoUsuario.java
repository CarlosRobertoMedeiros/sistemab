package br.com.roberto.negocio.servico;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import br.com.roberto.dao.UsuarioDaoImpl;
import br.com.roberto.model.Usuario;

@Stateless
public class ServicoUsuario {
	
	@Inject
	UsuarioDaoImpl usuarioDao;
	
	
	public Usuario pesquisaUsuario(int id) {
		Usuario usuarioInterno = usuarioDao.buscaPorId(id, new Usuario());
		Usuario usuarioParaSalvar = new Usuario();
		if (usuarioInterno==null) {
			throw new RuntimeException("Usuario Não Encontrado");
		}
		
		try {
			BeanUtils.copyProperties(usuarioParaSalvar, usuarioInterno);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usuarioParaSalvar.setId(Long.valueOf(id));
		return usuarioParaSalvar;
	}

}
