package br.com.roberto;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



/**
 * 
 * @author roberto
 *
 */
@ApplicationPath("/v1")
public class SistemabApplication extends Application{
	 
	
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
