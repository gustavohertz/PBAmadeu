package org.example.crud;

import org.example.Funcionario;

import java.util.Optional;
import java.util.Scanner;
import static org.example.crud.CriarFuncionario.funcionarios;
import static org.example.crud.VisualizarFuncionario.visualizarFuncionario;



public class ModificarFuncionario {
    public static void modificarFuncionario(int id, String nome, int idade, double salario) {
        Optional<Funcionario> funcionarioOptional = getFuncionarioById(id);
        Scanner sc = new Scanner(System.in);

        if(funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            funcionario.setNome(nome);
            funcionario.setIdade(idade);
            funcionario.setSalario(salario);
        }

    }

    private static Optional<Funcionario> getFuncionarioById(int id) {
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst();
    }
}
