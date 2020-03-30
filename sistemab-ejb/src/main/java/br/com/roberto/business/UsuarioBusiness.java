package br.com.roberto.business;

import java.util.List;

import javax.ejb.Local;

import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;

@Local
public interface UsuarioBusiness {
	
	public List<UsuarioDto> listaTodos(int inicio, int tamanho); 
	
	public UsuarioDto listaPorId(int id); 

	public UsuarioDto adiciona(Usuario usuario);

	public UsuarioDto atualiza(int id, Usuario usuario);

	public boolean remove(int id);
}
