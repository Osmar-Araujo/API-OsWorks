package com.algaworks.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.api.exceptionhandler.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente ClienteExistente = repository.findByEmail(cliente.getEmail());
		
		if(ClienteExistente != null && !ClienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
		}
		
		return repository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		repository.deleteById(clienteId);
	}
}
