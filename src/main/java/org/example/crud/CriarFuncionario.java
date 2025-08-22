package org.example.crud;

import org.example.Funcionario;

import java.util.*;

public class CriarFuncionario {
    public static List<Funcionario> funcionarios = new ArrayList<>();
    static Scanner digitar = new Scanner(System.in);

    public static void criarFuncionario() {
        try {
            int id = lerId();
            String nome = lerNome();
            int idade = lerIdade();
            double salario = lerSalario();

            Funcionario novoFuncionario = new Funcionario(id, nome, idade, salario);

            funcionarios.add(novoFuncionario);

            System.out.println("\nFuncionário cadastrado com sucesso!");
            System.out.println("Dados: " + novoFuncionario.toString());
            System.out.println("Total de funcionários no sistema: " + funcionarios.size());

        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Use o tipo correto de dado.");
            digitar.nextLine();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static int lerId() {
        System.out.print("Digite o ID do funcionário: ");
        int id = digitar.nextInt();
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser um número positivo.");
        }
        if (funcionarios.stream().anyMatch(f -> f.getId() == id)) {
            throw new IllegalArgumentException("Já existe um funcionário com esse ID.");
        }
        return id;
    }

    private static String lerNome() {
        digitar.nextLine(); // limpa buffer
        System.out.print("Digite o nome completo do funcionário: ");
        String nome = digitar.nextLine().trim();
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        return nome;
    }

    private static int lerIdade() {
        System.out.print("Digite a idade do funcionário: ");
        int idade = digitar.nextInt();
        if (idade < 16 || idade > 100) {
            throw new IllegalArgumentException("A idade deve estar entre 16 e 100 anos.");
        }
        return idade;
    }

    private static double lerSalario() {
        System.out.print("Digite o salário do funcionário: ");
        double salario = digitar.nextDouble();
        if (salario < 1300.0) { // Exemplo: salário mínimo
            throw new IllegalArgumentException("O salário deve ser maior ou igual ao salário mínimo (R$ 1300.00).");
        }
        return salario;
    }
}
