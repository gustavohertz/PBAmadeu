package com.yourcompany.e2e.tests;

import com.yourcompany.e2e.driver.DriverFactory;
import com.yourcompany.e2e.pages.FuncionarioFormPage;
import com.yourcompany.e2e.pages.FuncionarioListPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FuncionarioE2ETest {
    private WebDriver driver;
    private FuncionarioListPage listPage;
    private FuncionarioFormPage formPage;
    private final String baseUrl = "http://localhost:8080"; // Ajuste conforme seu servidor

    @BeforeEach
    public void setup() {
        // Usa a Factory agora (Requisito: flexibilidade de navegadores)
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        listPage = new FuncionarioListPage(driver);
        formPage = new FuncionarioFormPage(driver);

        // Limpeza de estado para garantir independência dos testes
        driver.get(baseUrl + "/index.html");
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("localStorage.clear()");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) driver.quit();
    }

    // REQUISITO: Teste Parametrizado (Testa múltiplos cenários de CPF inválido)
    @ParameterizedTest
    @ValueSource(strings = {"123", "abc.def.ghi-jk", "", "111111111"})
    @DisplayName("Deve falhar ao tentar cadastrar com CPFs inválidos")
    public void testeValidacaoCpfInvalido(String cpfInvalido) {
        listPage.open(baseUrl);
        listPage.clickNovo();

        // Tenta preencher com dados inválidos vindos da fonte de dados (@ValueSource)
        formPage.fillForm("Teste Parametrizado", cpfInvalido);
        formPage.submit();

        // Asserção: Se o CPF é inválido, o sistema NÃO deve ter voltado para a Home (index.html)
        // Deve permanecer no form ou mostrar erro.
        boolean aindaNoFormulario = driver.getCurrentUrl().contains("form.html");
        Assertions.assertTrue(aindaNoFormulario, "Deveria bloquear o cadastro com CPF: " + cpfInvalido);
    }

    @Test
    @DisplayName("Fluxo Feliz: Criar e Listar Funcionário")
    public void fluxoCriarListar() {
        listPage.open(baseUrl);
        listPage.clickNovo();

        // Preenche com salário agora
        formPage.fillForm("Maria Souza", "123.456.789-00");
        formPage.submit();

        listPage.open(baseUrl);
        Assertions.assertTrue(listPage.listarNomes().contains("Maria Souza"));
    }
}