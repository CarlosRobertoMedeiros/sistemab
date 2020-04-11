package br.com.roberto.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.roberto.dto.UsuarioDto;
import br.com.roberto.model.Usuario;
import br.com.roberto.negocio.exception.DadosNaoEncontradosException;

@Local
public interface UsuarioNegocio {
	
	List<UsuarioDto> listaTodos(int inicio, int tamanho) throws DadosNaoEncontradosException; 
	
	UsuarioDto listaPorId(Long id) throws DadosNaoEncontradosException; 

	UsuarioDto adiciona(Usuario usuario);

	UsuarioDto atualiza(Long id, Usuario usuario);

	boolean remove(Long id);
}
