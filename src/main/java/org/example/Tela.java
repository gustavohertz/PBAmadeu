package org.example;

import java.util.Scanner;

import static org.example.crud.CriarFuncionario.criarFuncionario;
import static org.example.crud.DeletarFuncionario.*;
import static org.example.crud.ModificarFuncionario.modificarFuncionario;
import static org.example.crud.VisualizarFuncionario.visualizarFuncionario;

public class Tela {
    static Scanner digitar = new Scanner(System.in);
    private static boolean sistemaOnilne = true;

    public static void tela() {
        while (sistemaOnilne == true) {
            System.out.println("Menu");
            System.out.println("1. Adicionar novo funcionario");
            System.out.println("2. Visualizar funcionarios");
            System.out.println("3. Modificar Funcionario");
            System.out.println("4. Remover Funcionario");
            System.out.println("0. Sair");
            int input = digitar.nextInt();

            if (input == 1) {
                criarFuncionario();
            } else if (input == 2) {
                visualizarFuncionario();
            } else if (input == 3) {
                visualizarFuncionario();
                System.out.println("Digite o ID do funcionário que deseja modificar:");
                int idParaModificar = digitar.nextInt();
                digitar.nextLine();

                System.out.println("Digite o novo nome:");
                String novoNome = digitar.nextLine();

                System.out.println("Digite a novo CPF:");
                String novoCpf = digitar.nextLine();

                System.out.println("Digite o novo salário:");
                double novoSalario = digitar.nextDouble();

                modificarFuncionario(idParaModificar, novoNome, novoCpf, novoSalario);
            } else if (input == 4) {
                deletarFuncionario();
            } else if (input == 0) {
                System.out.println("Obrigado pela preferencia");
                sistemaOnilne = false;
            } else {
                System.out.println("Digite uma opção valida");
            }
        }
    }
}