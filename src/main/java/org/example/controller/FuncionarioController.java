package org.example.controller;

import org.example.Funcionario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    // Nossa lista oficial (Banco de dados em memória)
    private static List<Funcionario> funcionarios = new ArrayList<>();

    // --- MÉTODOS DE API ---

    @GetMapping
    public List<Funcionario> listar() {
        // Log para você ver a requisição chegando
        System.out.println("🔎 O Frontend pediu a lista. Enviando " + funcionarios.size() + " funcionários.");
        return funcionarios;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Funcionario novoFuncionario) {
        try {
            // Validações
            if (novoFuncionario.getId() <= 0) return ResponseEntity.badRequest().body("ID deve ser positivo.");
            if (funcionarios.stream().anyMatch(f -> f.getId() == novoFuncionario.getId())) return ResponseEntity.badRequest().body("ID já existe.");
            if (funcionarios.stream().anyMatch(f -> f.getCpf().equals(novoFuncionario.getCpf()))) return ResponseEntity.badRequest().body("CPF já existe.");
            if (novoFuncionario.getSalario() < 1300) return ResponseEntity.badRequest().body("Salário inválido.");

            funcionarios.add(novoFuncionario);

            // Chamamos o método auxiliar para imprimir no console (substitui o VisualizarFuncionario)
            imprimirListaNoConsole();

            return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
        }
    }

    // Mantive os métodos DELETE e PUT simplificados para focar na visualização...
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable int id) {
        funcionarios.removeIf(f -> f.getId() == id);
        imprimirListaNoConsole(); // Imprime log após excluir
        return ResponseEntity.ok("Removido");
    }

    // --- MÉTODO AUXILIAR (Substitui sua classe antiga) ---
    private void imprimirListaNoConsole() {
        System.out.println("\n--- LISTA ATUALIZADA (BACKEND) ---");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario f : funcionarios) {
                System.out.println(f.toString());
            }
        }
        System.out.println("----------------------------------\n");
    }
}