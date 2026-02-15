import com.model.TaskModel;
import com.view.TaskView;
import com.repository.TaskRepository;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;

public class Main {
    public static void main(String[] args) throws Exception {

        TaskView view = new TaskView();
        TaskRepository repository = new TaskRepository();
        Scanner teclado = new Scanner(System.in);
        int opcao = 0;
        while (opcao != -1) {
            System.out.println(" SISTEMA DE GERENCIAMENTO DE TAREFAS");
            System.out.println(" 1 - Adicionar tarefa ");
            System.out.println(" 2 - Listar tarefas ");
            System.out.println(" 3 - Atualizar tarefa ");
            System.out.println(" 4 - Remover tarefa ");
            System.out.println(" 0 - Sair do sistema ");

            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 1:
                    TaskModel novaTarefa = view.adicionarTarefa();
                    repository.create(novaTarefa);
                    break;

                case 2:
                    List<TaskModel> lista = repository.readAll();
                    view.listarTarefa(lista);
                    break;
                case 3:
                    TaskModel tarefaAtualizada = view.atualizarTarefa();
                    repository.update(tarefaAtualizada);
                    break;
                case 4:
                    int idParaExcluir = view.excluirTarefa();
                    repository.delete(idParaExcluir);
                    System.out.println("Tarefa removida com sucesso");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    opcao = -1; // Quebra o laço while
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
