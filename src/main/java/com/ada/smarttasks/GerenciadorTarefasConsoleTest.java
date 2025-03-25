package com.ada.smarttasks;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ada.smarttasks.model.StatusTarefa;
import com.ada.smarttasks.model.Tarefa;
import com.ada.smarttasks.service.TarefaService;

import java.time.LocalDateTime;
import java.util.Scanner;

@Component
public class GerenciadorTarefasConsoleTest implements ApplicationRunner {

    private final TarefaService tarefaService;
    
    public GerenciadorTarefasConsoleTest(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== GERENCIADOR DE TAREFAS INTELLIGENTE ===");
        
        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Cadastrar nova tarefa");
            System.out.println("2. Listar todas as tarefas");
            System.out.println("3. Filtrar tarefas por status");
            System.out.println("4. Listar tarefas próximas do prazo");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    cadastrarTarefa(scanner);
                    break;
                case 2:
                    listarTodasTarefas();
                    break;
                case 3:
                    filtrarPorStatus(scanner);
                    break;
                case 4:
                    listarTarefasProximas();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarTarefa(Scanner scanner) {
        System.out.println("\n--- CADASTRAR NOVA TAREFA ---");
        
        System.out.print("Título (min 5 caracteres): ");
        String titulo = scanner.nextLine();
        
        System.out.print("Descrição (min 10 caracteres): ");
        String descricao = scanner.nextLine();
        
        System.out.print("Data limite (AAAA-MM-DDTHH:MM): ");
        LocalDateTime dataLimite = LocalDateTime.parse(scanner.nextLine());
        
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setDataLimite(dataLimite);
        tarefa.setStatus(StatusTarefa.PENDENTE);
        
        try {
            Tarefa tarefaSalva = tarefaService.criarTarefa(tarefa);
            System.out.println("Tarefa cadastrada com sucesso! ID: " + tarefaSalva.getId());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private void listarTodasTarefas() {
        System.out.println("\n--- TODAS AS TAREFAS ---");
        tarefaService.listarTodas().forEach(this::imprimirTarefa);
    }

    private void filtrarPorStatus(Scanner scanner) {
        System.out.println("\n--- FILTRAR POR STATUS ---");
        System.out.println("Status disponíveis: PENDENTE, EM_ANDAMENTO, CONCLUIDA");
        System.out.print("Digite o status: ");
        
        try {
            StatusTarefa status = StatusTarefa.valueOf(scanner.nextLine().toUpperCase());
            tarefaService.filtrarPorStatus(status).forEach(this::imprimirTarefa);
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido!");
        }
    }

    private void listarTarefasProximas() {
        System.out.println("\n--- TAREFAS PRÓXIMAS DO PRAZO (próximas 2 horas) ---");
        tarefaService.buscarTarefasProximasDoPrazo().forEach(this::imprimirTarefa);
    }

    private void imprimirTarefa(Tarefa tarefa) {
        System.out.println("\nID: " + tarefa.getId());
        System.out.println("Título: " + tarefa.getTitulo());
        System.out.println("Descrição: " + tarefa.getDescricao());
        System.out.println("Data Limite: " + tarefa.getDataLimite());
        System.out.println("Status: " + tarefa.getStatus());
        System.out.println("Última Atualização: " + tarefa.getDataAtualizacao());
    }
}