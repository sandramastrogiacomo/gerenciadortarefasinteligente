package com.ada.smarttasks.controller;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ada.smarttasks.model.StatusTarefa;
import com.ada.smarttasks.model.Tarefa;
import com.ada.smarttasks.repository.TarefaRepository;
import com.ada.smarttasks.service.TarefaService;
import com.ada.smarttasks.dto.TarefaRequest;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TarefaController {
    
    private final TarefaService tarefaService;
    
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Autowired
    private TarefaRepository tarefarepository;
    
    @GetMapping
    public ResponseEntity<List<Tarefa>> getAll() {
        List<Tarefa> tarefas = tarefaService.listarTodas();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Tarefa>> getByStatus(@PathVariable StatusTarefa status) {
        List<Tarefa> tarefas = tarefaService.filtrarPorStatus(status);
        return ResponseEntity.ok(tarefas);
    }
    
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Tarefa>> getByTitulo(@PathVariable String titulo){
    	return ResponseEntity.ok(tarefarepository.findAllByTituloContainingIgnoreCase(titulo));
    } 
     
    @GetMapping("/proximas")
    public ResponseEntity<List<Tarefa>> getProximasDoPrazo() {
        List<Tarefa> tarefas = tarefaService.buscarTarefasProximasDoPrazo();
        return ResponseEntity.ok(tarefas);
    }

    @PostMapping
    public ResponseEntity<Tarefa> post(@Valid @RequestBody TarefaRequest tarefaRequest) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(tarefaRequest.getTitulo());
        tarefa.setDescricao(tarefaRequest.getDescricao());
        tarefa.setDataLimite(tarefaRequest.getDataLimite());
        tarefa.setStatus(tarefaRequest.getStatus());
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tarefaService.criarTarefa(tarefa));
    }

    @PutMapping
    public ResponseEntity<Tarefa> put(@Valid @RequestBody Tarefa tarefa){
    	return tarefarepository.findById(tarefa.getId())
    			.map(resposta -> ResponseEntity.status(HttpStatus.OK)
    					.body(tarefarepository.save(tarefa)))
    			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); 
    }
                
	@DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void delete(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
    }
}
