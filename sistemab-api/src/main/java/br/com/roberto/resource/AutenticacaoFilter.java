package br.com.roberto.resource;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


@Provider
public class AutenticacaoFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	
	@Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		Method method = resourceInfo.getResourceMethod();
		
		if (!method.isAnnotationPresent(PermitAll.class)) {
			
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
						.entity("Acesso Bloqueado Para Todos os Usuarios !")
						.build());
				return;
			}
			
			//Captura Requisição do Cabeçalho
			final List<String> headers = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
              
            //Verifica Se os Dados do Cabeçalho foram enviados
            if (headers==null) {
            	requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Favor Informar as Credenciais Para Acessar o Recurso")
                        .build());
                    return;
            }
			final String authorization = headers.get(0);
              
            //Caso não tenha autorização, bloqueia o acesso
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Você Não Pode Acessar Esse Recurso")
                    .build());
                return;
            }
              
            if (!method.isAnnotationPresent(RolesAllowed.class)) {
            	requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
						.entity("Acesso Bloqueado Para Todos os Usuarios !")
						.build());
				return;
            }
            
            //Captura Usuário e Senha Encodado em Base64
            final String encodedUserPassword = authorization.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
              
            //Realiza o decoded do Usuario e Senha em Base64 
            byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			String usernameAndPassword = new String(decodedBytes);
  
            //Faz o Split para o usuario e senha
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
              
            //Verifica Permissão de Acesso
            if(method.isAnnotationPresent(RolesAllowed.class)){
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                  
                //é um usuário válido?
                if( ! isUsuarioPermitido(username, password, rolesSet))
                {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Você Não Pode Acessar Esse Recurso")
                        .build());
                    return;
                }
            }
        
			
		}

	}

	private boolean isUsuarioPermitido(String usuario, String senha, Set<String> rolesSet) {
		boolean isPermitido = false;
        
        //Step 1. TODO: Aqui eu vou testar a permissão no banco de dados
        //Se ambos baterem define a regra e continua ; caso contrario não permite
        //Step 2. TODO: Trazer a Regra do Usuário do banco de dados 
         
        if(usuario.equals("user") && senha.equals("password")){
            String userRole = "ADMIN";
             
            //Step 2. Verify user role
            if(rolesSet.contains(userRole)){
            	isPermitido = true;
            }
        }
        return isPermitido;
	}

}
