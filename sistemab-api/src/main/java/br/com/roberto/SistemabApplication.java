package br.com.roberto;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



/**
 * 
 * @author roberto
 *
 */

//TODO: Implementar o HATEOS
//TODO: Implementar o Swagger
//TODO: Implementar o Jacoco
//TODO: Implementar os testes

// https://www.youtube.com/watch?v=Vdk_tUhcJZM&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn&index=28
//TODO: Colocar o Backend no Docker
@ApplicationPath("/v1")
public class SistemabApplication extends Application{
	 
	
//	@Override
//	public Set<Class<?>> getClasses() {
//		Set<Class<?>> classes = new HashSet<Class<?>>();
//        classes.add(UsuarioResource.class);
//        return classes;
//	}
	
//	@Override
//	public Set<Object> getSingletons() {
//		Set<Object> set = new HashSet<>();
//	    set.add(new DadosNaoEncontradosExceptionMapper());
//	    //set.add(new GenericoExceptionMapper());
//	    return set;
//	}
	
	
	public SistemabApplication() {
	
	}
	
	//TODO: Passar as Configurações corretas do config 
//	public SistemabApplication() {
//		BeanConfig conf = new BeanConfig();
//	    conf.setTitle("NomeSis API");
//	    conf.setDescription("API de Cadastramento de Clientes");
//	    conf.setVersion("1.0.0");
//	    conf.setBasePath("/nome-sis-api/rest");
//	    conf.setResourcePackage("br.roberto.nome-sis-api.rest");
//	    conf.setScan(true);
//	    conf.setPrettyPrint(true);
//	}
}
