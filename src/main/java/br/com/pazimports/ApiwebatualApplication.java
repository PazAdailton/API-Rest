package br.com.pazimports;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.pazimports.ws.controller.TokenFilter;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;


@SpringBootApplication
public class ApiwebatualApplication {

    @Bean
    FilterRegistrationBean<Filter> filtroJwt() {
		
		FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
		
		frb.setFilter(new TokenFilter());
		
		frb.addUrlPatterns("/admin/*");
		
		
		return frb;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiwebatualApplication.class, args);
	}

}
