package com.algaworks.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.api.exceptionhandler.EntidadeNaoEncontradaException;
import com.algaworks.osworks.api.exceptionhandler.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.model.StatusOrdemServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.ComentarioRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private ClienteRepository cliRepository;
	
	@Autowired
	private ComentarioRepository comentRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = cliRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return repository.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		repository.save(ordemServico);
	}

	private OrdemServico buscar(Long ordemServicoId) {
		return repository.findById(ordemServicoId)
				.orElseThrow(()-> new NegocioException("Ordem de serviço não encontrada"));
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentRepository.save(comentario);
	}
}
