package com.algaworks.osworks.api.model;

import java.time.OffsetDateTime;

public class ComentarioModel {
	
	private Long id;
	private String decricao;
	private OffsetDateTime dataEnvio;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDecricao() {
		return decricao;
	}
	public void setDecricao(String decricao) {
		this.decricao = decricao;
	}
	public OffsetDateTime getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(OffsetDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	
}
