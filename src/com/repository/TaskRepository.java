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
        // String conteudo = new String(Files.readAllBytes(Paths.get(FILE_PATH))) ; -
        // depois
        return new JSONArray(conteudo);
    }

    // UPDATE: Altera um registro pelo ID

    // DELETE: Remove um registro pelo ID
}
