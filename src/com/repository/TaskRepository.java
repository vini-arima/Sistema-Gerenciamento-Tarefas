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
    public void create(TaskModel novaTarefa) throws IOException {
        List<TaskModel> listaDeTarefas = readAll();

        listaDeTarefas.add(novaTarefa);

        save(listaDeTarefas);
    }

    // READ : ler todo o arquivo
    public List<TaskModel> readAll() {
        List<TaskModel> listaDeTarefas = new ArrayList<>();
        try {
            // Verifica se arquivo existe antes de ler
            if (!Files.exists(Paths.get(FILE_PATH))) {
                return listaDeTarefas;
            }

            String conteudo = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            if (conteudo.isEmpty())
                return listaDeTarefas;

            JSONArray jsonArray = new JSONArray(conteudo);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                TaskModel tarefa = new TaskModel(
                        obj.optInt("id"), // mudei get
                        obj.optString("titulo"),
                        obj.optString("descricao"),
                        obj.optBoolean("status", false));

                listaDeTarefas.add(tarefa);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return listaDeTarefas;
    }

    // UPDATE: Altera um registro pelo ID
    public void update(int id, boolean novoStatus) throws IOException {
        List<TaskModel> lista = readAll();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {

            TaskModel novaTarefa = lista.get(i);
            if (novaTarefa.getId() == id) {

                novaTarefa.setStatus(novoStatus);

                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            try {
                save(lista);
            } catch (Exception e) {
                System.out.println("Erro ao Slavar arquivo" + e.getMessage());
            }
        } else {
            System.out.println("Erro: ID " + id + " não encontrado.");
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

    private void save(List<TaskModel> lista) throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (TaskModel tarefa : lista) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", tarefa.getId());
            jsonObject.put("titulo", tarefa.getTitulo()); // Assumindo getters na TaskModel
            jsonObject.put("descricao", tarefa.getDescricao());
            jsonObject.put("status", tarefa.getStatus());

            jsonArray.put(jsonObject);
        }

        Files.write(Paths.get(FILE_PATH), jsonArray.toString(2).getBytes());
    }
}