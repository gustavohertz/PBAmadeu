package org.example;

public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private double salario;

    // --- ADICIONE ESTE BLOCO (Obrigatório para o Spring Boot) ---
    public Funcionario() {
    }
    // ------------------------------------------------------------

    // O construtor que você já tinha (pode manter)
    public Funcionario(int id, String nome, String cpf, double salario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    // Certifique-se também de ter TODOS os Getters e Setters abaixo
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}