package com.repository;

import org.json.JSONArray;
import org.json.JSONObject;
import com.model.TaskModel;

import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static final String FILE_PATH = "dados.json";

    // CREATE : Adiciona o novo objeto
    public void create(int id, String nome) throws IOException {
        List<TaskModel> listaDeTarefas = readAll();

        TaskModel novaTarefa = new TaskModel(id, nome, null, false);

        listaDeTarefas.add(novaTarefa);

        save(listaDeTarefas);
    }

    // READ : ler todo o arquivo
    public List<TaskModel> readAll() {
        List<TaskModel> listaDeTarefas = new ArrayList<>();
        try {
            String conteudo = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray jsonArray = new JSONArray(conteudo);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                TaskModel tarefa = new TaskModel(
                        obj.getInt("id"),
                        obj.getString("titulo"),
                        obj.getString("descricao"),
                        obj.getBoolean("status"));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: ");
        }
        return listaDeTarefas;

    }

    // UPDATE: Altera um registro pelo ID
    public void update(int id, String novotitulo) throws IOException {
        List<TaskModel> lista = readAll();
        for (int i = 0; i < lista.size(); i++) {
            TaskModel tarefa = lista.get(i);

            if (tarefa.getId() == id) {
                tarefa.setTitulo(novotitulo);
                break;
            }
        }
        save(lista);
    }

    // DELETE: Remove um registro pelo ID
    public void delete(int id) {
        try {
            List<TaskModel> lista = readAll();
            int i;
            for (i = lista.size() - 1; i >= 0; i--) {
                if (lista.get(i).getId() == id) {
                    lista.remove(i);
                }
            }
            save(lista);
        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }

    }

    private void save(JSONArray lista) throws IOException {
        Files.write(Paths.get(FILE_PATH), lista.toString(2).getBytes());
    }

    // GAMBIARRA
    public void create(TaskModel novaTarefa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    public void update(TaskModel tarefaAtualizada) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    private void save(List<TaskModel> listaDeTarefas) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
