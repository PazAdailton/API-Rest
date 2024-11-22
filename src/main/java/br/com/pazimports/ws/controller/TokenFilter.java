package br.com.pazimports.ws.controller;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class TokenFilter extends GenericFilterBean {

	

	
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		
	String header = req.getHeader("Authorization");
	
	if(header == null || !header.startsWith("Bearer ")) {
		throw new ServletException("Token inexistente ou inválido");
	}
		
	String token = header.substring(7);
	
	 SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
		//verficar se o token é válida
	try {
        // Verifica se o token é válido usando a chave de assinatura
		   Claims claims = Jwts.parserBuilder()
                .setSigningKey(LoginController.jwtkey)  // Use a chave correta
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Você pode usar as claims aqui se necessário
        System.out.println("Token claims: " + claims);

    } catch (SignatureException e) {
        throw new ServletException("Token inválido ou assinatura incorreta");
    } catch (Exception e) {
        throw new ServletException("Erro ao validar o token", e);
    }
	
	chain.doFilter(request, response);
	
	}
}