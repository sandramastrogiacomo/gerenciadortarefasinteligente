package com.ada.smarttasks.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import com.ada.smarttasks.model.StatusTarefa;
import com.ada.smarttasks.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    List<Tarefa> findAllByTituloContainingIgnoreCase(String titulo);
    
    List<Tarefa> findByStatus(StatusTarefa status);

    List<Tarefa> findAllByOrderByDataLimiteAsc();
    
    @Query("SELECT t FROM Tarefa t WHERE t.dataLimite BETWEEN :now AND :limite")
    List<Tarefa> findTarefasComPrazosProximos(
            @Param("now") LocalDateTime now,
            @Param("limite") LocalDateTime limite);
    
    @Async
    @Query("SELECT t FROM Tarefa t WHERE t.status = 'PENDENTE' AND t.dataLimite < :now")
    Future<List<Tarefa>> findTarefasAtrasadas(@Param("now") LocalDateTime now);
}