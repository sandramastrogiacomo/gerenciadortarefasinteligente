package com.ada.smarttasks.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ada.smarttasks.model.Tarefa;
import com.ada.smarttasks.repository.TarefaRepository;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificacaoService {
    
    private final TarefaRepository tarefaRepository;
    
    public NotificacaoService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }
    
    @Async
    @Scheduled(fixedRate = 300000) 
    public CompletableFuture<Void> verificarNotificacoes() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime limite = agora.plusHours(2);
        
        tarefaRepository.findTarefasComPrazosProximos(agora, limite)
            .forEach(this::enviarNotificacao);
            
        return CompletableFuture.completedFuture(null);
    }
    
    @Async
    public CompletableFuture<Void> enviarNotificacao(Tarefa tarefa) {
           System.out.printf("Notificação: Tarefa '%s' vence em %s%n", 
            tarefa.getTitulo(), tarefa.getDataLimite());
        return CompletableFuture.completedFuture(null);
    }
}