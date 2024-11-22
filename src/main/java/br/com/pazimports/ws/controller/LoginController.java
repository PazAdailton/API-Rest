package br.com.pazimports.ws.controller;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import br.com.pazimports.ws.model.Usuario;
import br.com.pazimports.ws.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;

@RestController("/admin/")
public class LoginController {

	@Autowired
	UsuarioService usuarioService;
	
	
	static SecretKey jwtkey;
	
	
	@RequestMapping(value="/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public loginResponse  autenticar(@RequestBody Usuario usuario) throws ServletException {
		
		
		
		if(usuario.getNome() == null || usuario.getSenha() == null) {
			throw new ServletException("Nome e senha obrigatórios");
		}
		
		//consulta no banco
		Usuario usuAutenticado = usuarioService.buscarPorNome(usuario.getNome());
		
		if(usuAutenticado == null) {
			throw new ServletException("Usuário não encontrado");
		}
		
		if(!usuAutenticado.getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Senha inválida");
		}
		
		 if (jwtkey == null) {
	            jwtkey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Cria a chave uma vez
	        }
		
	  String token =  Jwts.builder()
			  .setSubject(usuAutenticado.getNome())
			  .signWith(jwtkey)
			  .setExpiration(new Date(System.currentTimeMillis() +1 * 30  * 60 * 100))
			  .compact();
	  
	  return new loginResponse(token);
    }

    // Classe separada para a resposta com o token
   
	
	
		public static class loginResponse {

        public String token;


		public loginResponse(String token) {
            this.token = token;
        }
    }
	
	

	
}
