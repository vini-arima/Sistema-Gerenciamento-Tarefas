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
        JSONObject novo = new JSONObject();
        novo.put("id", id);
        novo.put("nome", nome);
        listaDeTarefas.put(novo);
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
        JSONArray lista = readAll();
        for (int i = 0; i < lista.length(); i++) {
            if (lista.getJSONObject(i).getInt("id") == id) {
                lista.getJSONObject(i).put("nome", novotitulo);
            }
        }
        save(lista);
    }

    // DELETE: Remove um registro pelo ID
    public void delete(int id) {
        try {
            JSONArray lista = readAll();
            for (int i = 0; i < lista.length(); i++) {
                if (lista.getJSONObject(i).getInt("id") == id) {
                    lista.remove(i);
                }
            }
            save(lista);
        } catch (IOException e) {
            System.out.println("Erro ao deletar: ");
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
