import com.model.TaskModel;
import com.view.TaskView;
import com.repository.TaskRepository;
import java.util.List;
import java.util.Scanner;
import utils.Color;

public class Main {
    public static void main(String[] args) throws Exception {
        TaskView view = new TaskView();
        TaskRepository repository = new TaskRepository();
        Scanner teclado = new Scanner(System.in);
        int opcao = 0;
        while (opcao != -1) {
            System.out.println(Color.ANSI_RED + " SISTEMA DE GERENCIAMENTO DE TAREFAS" + Color.ANSI_RESET);
            System.out.println(Color.ANSI_RED + " 1 - Adicionar tarefa " + Color.ANSI_RESET);
            System.out.println(Color.ANSI_RED + " 2 - Listar tarefas " + Color.ANSI_RESET);
            System.out.println(Color.ANSI_RED + " 3 - Atualizar tarefa " + Color.ANSI_RESET);
            System.out.println(Color.ANSI_RED + " 4 - Remover tarefa " + Color.ANSI_RESET);
            System.out.println(Color.ANSI_RED + " 0 - Sair do sistema " + Color.ANSI_RESET);

            System.out.print(Color.ANSI_YELLOW + "Escolha uma opção: " + Color.ANSI_RESET);
            opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 1:
                    TaskModel novaTarefa = view.adicionarTarefa();
                    repository.create(novaTarefa);
                    System.out.println(Color.ANSI_GREEN + "Sucesso: Tarefa criada!" + Color.ANSI_RESET);
                    break;

                case 2:
                    List<TaskModel> lista = repository.readAll();
                    if (lista.isEmpty()) {
                        System.out.println("Aviso: Nenhuma tarefa encontrada.");
                    } else {
                        view.listarTarefa(lista);
                    }
                    break;

                case 3:
                    TaskModel tarefaAtualizada = view.atualizarTarefa();
                    repository.update(tarefaAtualizada.getId(), tarefaAtualizada.getStatus());
                    break;

                case 4:
                    int idParaExcluir = view.excluirTarefa();
                    repository.delete(idParaExcluir);
                    System.out.println("Tarefa removida com sucesso");
                    break;
                case 0:
                    System.out.println(Color.ANSI_GREEN + "Saindo..." + Color.ANSI_RESET);
                    opcao = -1; // Quebra o laço while
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
