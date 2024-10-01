package br.com.pazimports.ws.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pazimports.ws.model.Cliente;
import br.com.pazimports.ws.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;


	
	//Regra Negocios
	public Cliente cadastrar(Cliente cliente) {
		
	
		return clienteRepository.save(cliente);
		
	}
	
	public Collection<Cliente> buscarTodos(){
		
		return clienteRepository.findAll();
	}
	
	public void excluir(Integer id) {
	    clienteRepository.deleteById(id);
	}

	
	public Optional<Cliente> buscaPorId(Integer id) {
	    return clienteRepository.findById(id);
	}

	
	public Cliente alterar(Cliente cliente) {
		
		return clienteRepository.save(cliente);
		
	}
	
	
	
	
}
