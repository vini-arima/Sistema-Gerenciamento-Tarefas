package com.view;

import java.util.List;
import java.util.Scanner;
import com.model.TaskModel;

public class TaskView {

    private Scanner scan = new Scanner(System.in);

    public void adicionarTarefa() {
        System.out.println("--- NOVA TAREFA ---");

        System.out.print("ID: ");
        int id = Integer.parseInt(scan.nextLine());

        System.out.print("Título: ");
        String titulo = scan.nextLine();

        System.out.print("Descrição: ");
        String descricao = scan.nextLine();

        String status = "Pendente";

        // return new TaskModel(id, titulo, descricao, status);
    }

    public void listarTarefa(List<TaskModel> tarefas) {
        System.out.println("\n--- LISTA DE TAREFAS ---");

        if (tarefas == null || tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa no arquivo JSON.");
        } else {
            for (int i = 0; i < tarefas.size(); i++) {

                // 4. Cria o objeto 'tarefa_atual' para pegar cada item da lista
                TaskModel tarefa_atual = tarefas.get(i);

                // 5. Imprime os dados
                System.out.println("ID: " + tarefa_atual.getId());
                System.out.println("Título: " + tarefa_atual.getTitulo());
                System.out.println("-----------------------");
            }
        }

    }

    public void atualizarTarefa() {
        System.out.println("--- ATUALIZAR TAREFA ---");
        System.out.println("Informe o Id: ");
        int id = Integer.parseInt(scan.nextLine());

        System.out.print("Novo Status (Pendente/Concluido): ");
        boolean status = scan.nextBoolean();

        TaskModel t = new TaskModel(null, null, id, status);

    }

    public int excluirTarefa() {
        System.out.println("--- EXCLUIR TAREFA ---");
        System.out.print("Informe o ID para excluir: ");
        int id = Integer.parseInt(scan.nextLine());
        return id;
    }
}
