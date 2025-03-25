package com.ada.smarttasks.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import com.ada.smarttasks.model.StatusTarefa;

public class TarefaRequest {
    
    @NotBlank
    @Size(min = 5, max = 100)
    private String titulo;
    
    @NotBlank
    @Size(min = 10, max = 1000)
    private String descricao;
    
    @FutureOrPresent
    private LocalDateTime dataLimite;
    
    private StatusTarefa status;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDateTime dataLimite) {
		this.dataLimite = dataLimite;
	}

	public StatusTarefa getStatus() {
		return status;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

   
}