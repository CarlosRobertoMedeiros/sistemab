package br.com.roberto.negocio.servico;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import br.com.roberto.dao.UsuarioDao;
import br.com.roberto.model.Usuario;
import br.com.roberto.negocio.exception.DadosNaoEncontradosException;

@Stateless
public class UsuarioServico {
	
	@Inject
	UsuarioDao usuarioDao;
	
	
	public Usuario pesquisaUsuario(Long id) throws DadosNaoEncontradosException {
		Usuario usuarioInterno = usuarioDao.buscaPorId(id);
		Usuario usuarioParaSalvar = new Usuario();
		if (usuarioInterno==null) {
			throw new DadosNaoEncontradosException("Usuario Não Encontrado");
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


	public Usuario atualiza(Long id, Usuario usuario) {
		Usuario usuarioParaSalvar = usuario;
		try {
			usuarioParaSalvar.setId(id);
			Usuario usuarioSalvo = usuarioDao.salvar(usuarioParaSalvar);
			return usuarioSalvo;
		
		} catch (Exception e) {
			new RuntimeException(e.getCause()+" "+e.getMessage());
		}
		return null;
	}


	public boolean remove(Long id) {
		Usuario usuarioBanco = this.pesquisaUsuario(id);
		return usuarioDao.remove(usuarioBanco.getId());
	}

}


