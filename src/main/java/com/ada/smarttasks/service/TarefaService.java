package com.ada.smarttasks.service;

import org.springframework.stereotype.Service;

import com.ada.smarttasks.model.StatusTarefa;
import com.ada.smarttasks.model.Tarefa;
import com.ada.smarttasks.repository.TarefaRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    
    private final TarefaRepository tarefaRepository;
    
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }
    
    public Tarefa criarTarefa(Tarefa tarefa) {
        if (tarefa.getStatus() == null) {
            tarefa.setStatus(StatusTarefa.PENDENTE);
        }
        return tarefaRepository.save(tarefa);
    }
    
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAllByOrderByDataLimiteAsc();
    }
    
    public List<Tarefa> filtrarPorStatus(StatusTarefa status) {
        return Optional.ofNullable(status)
                .map(tarefaRepository::findByStatus)
                .orElseGet(tarefaRepository::findAll);
    }
    
    public List<Tarefa> buscarTarefasProximasDoPrazo() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime limite = agora.plusHours(2);
        return tarefaRepository.findTarefasComPrazosProximos(agora, limite);
    }
    
    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id).orElse(null);
    }
    
    public Tarefa atualizarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

	@Transactional
	public void excluirTarefa(Long id) {
		if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Tarefa não encontrada com ID: " + id);
        }
    }
		
		
	}
