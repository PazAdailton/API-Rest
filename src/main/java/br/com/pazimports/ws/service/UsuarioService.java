package br.com.pazimports.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pazimports.ws.model.Usuario;
import br.com.pazimports.ws.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario cadastrarUsuario(Usuario usuario) {
		
		return usuarioRepository.save(usuario) ;
	}
	
	public Usuario buscarPorNome(String nome) {
		
		return usuarioRepository.buscarPorNome(nome);
	}

	
}
