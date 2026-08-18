package br.edu.fatecpg.JavaJDBC.model;

public class Curso {
    private int id;
    private String nome;
    private String periodo;

    public Curso(int id, String nome, String periodo) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Período: " + periodo;
    }



}


