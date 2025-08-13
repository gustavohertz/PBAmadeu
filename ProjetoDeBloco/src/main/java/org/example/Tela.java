package org.example;

import java.util.Scanner;

import static org.example.crud.CriarFuncionario.criarFuncionario;
import static org.example.crud.DeletarFuncionario.deletarFuncionario;
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
                modificarFuncionario();
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