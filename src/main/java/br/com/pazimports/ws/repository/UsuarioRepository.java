package br.com.pazimports.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.pazimports.ws.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value="select u from Usuario u where u.nome =:pnome ")  
	public Usuario buscarPorNome(@Param("pnome") String  nome);
	
}
