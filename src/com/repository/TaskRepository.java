package com.repository;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static final String FILE_PATH = "dados.json";

    // CREATE : Adiciona o novo objeto
    public void create(int id, String nome) throws IOException {
        JSONArray lista = readAll();
        JSONObject novo = new JSONObject();
        novo.put("id", id);
        novo.put("nome", nome);
        lista.put(novo);
        save(lista);
    }

    // READ : ler todo o arquivo
    public JSONArray readAll() {
        try {
            String conteudo = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            return new JSONArray(conteudo);

        } catch (IOException e) {
            return new JSONArray();
        }
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
    public void delete(int id) throws IOException {
        JSONArray lista = readAll();
        for (int i = 0; i < lista.length(); i++) {
            if (lista.getJSONObject(i).getInt("id") == id) {
                lista.remove(i);
            }
        }
        save(lista);
    }

    private void save(JSONArray lista) throws IOException {
        Files.write(Paths.get(FILE_PATH), lista.toString(2).getBytes());
    }
}
