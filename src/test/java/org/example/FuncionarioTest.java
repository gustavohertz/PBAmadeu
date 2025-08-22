package org.example;

import net.jqwik.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.example.crud.CriarFuncionario.funcionarios;
import org.example.Funcionario;
import org.example.crud.CriarFuncionario;

class FuncionarioTest {

    @BeforeEach
    void limparLista() {
        funcionarios.clear();
    }

    // ---------- Testes Unitários ----------

    @Test
    void naoDevePermitirIdDuplicado() {
        Funcionario f1 = new Funcionario(1, "João", 30, 2000);
        funcionarios.add(f1);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            // Tentando criar outro com mesmo ID
            funcionarios.add(new Funcionario(1, "Maria", 25, 2500));
        });

        assertEquals("Já existe um funcionário com esse ID.", ex.getMessage());
    }

    @Test
    void naoDeveAceitarIdadeInvalida() {
        assertThrows(IllegalArgumentException.class, () -> new Funcionario(2, "Pedro", 10, 1500));
        assertThrows(IllegalArgumentException.class, () -> new Funcionario(3, "Ana", 150, 1500));
    }

    @Test
    void naoDeveAceitarSalarioAbaixoDoMinimo() {
        assertThrows(IllegalArgumentException.class, () -> new Funcionario(4, "Lucas", 25, 1000));
    }

    @Test
    void deveCriarFuncionarioValido() {
        Funcionario f = new Funcionario(5, "Carlos", 40, 3000);
        assertNotNull(f);
        assertEquals("Carlos", f.getNome());
    }


    // ---------- Testes Baseados em Propriedades (jqwik) ----------

    @Property
    void deveAceitarNomesValidos(@ForAll("nomesValidos") String nome) {
        Funcionario f = new Funcionario(6, nome, 30, 2000);
        assertNotNull(f);
    }

    @Provide
    Arbitrary<String> nomesValidos() {
        return Arbitraries.strings()
                .withCharRange('a', 'z')
                .ofMinLength(3)
                .ofMaxLength(30);
    }

    @Property
    void salarioDeveSerSempreAcimaDoMinimo(@ForAll("salariosValidos") double salario) {
        Funcionario f = new Funcionario(7, "Teste", 25, salario);
        assertTrue(f.getSalario() >= 1300.0);
    }

    @Provide
    Arbitrary<Double> salariosValidos() {
        return Arbitraries.doubles().between(1300, 20000);
    }

    @Property
    void deveFalharComSalarioInvalido(@ForAll double salario) {
        Assume.that(salario < 1300);
        assertThrows(IllegalArgumentException.class, () -> new Funcionario(8, "Erro", 25, salario));
    }

    @Property
    void deveFalharComIdadeInvalida(@ForAll int idade) {
        Assume.that(idade < 16 || idade > 100);
        assertThrows(IllegalArgumentException.class, () -> new Funcionario(9, "Erro", idade, 2000));
    }
}
