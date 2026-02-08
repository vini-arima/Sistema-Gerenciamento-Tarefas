package com.model;

public class TaskModel {
    String titulo;
    String descricao;
    int id;
    boolean status;

    public TaskModel(String titulo, String descricao, int id, boolean status) {
        this.titulo = titulo;
        this.id = id;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id; // CÓDIGO DA TAREFA
    }

    public boolean getStatus() {
        return status; // FEITO OU NÃO FEITO
    }
}
