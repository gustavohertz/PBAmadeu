package com.yourcompany.e2e.tests;

import com.yourcompany.e2e.pages.FuncionarioFormPage;
import com.yourcompany.e2e.pages.FuncionarioListPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FuncionarioE2ETest {
    private WebDriver driver;
    private FuncionarioListPage listPage;
    private FuncionarioFormPage formPage;
    // Ajuste para o endereço local onde você serve o index.html
    private final String baseUrl = "http://localhost:8080";

    @BeforeAll
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        listPage = new FuncionarioListPage(driver);
        formPage = new FuncionarioFormPage(driver);
        // limpar localStorage para testes consistentes (apenas para o front demo)
        driver.get(baseUrl + "/index.html");
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("localStorage.clear()");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void fluxoCriarListarExcluir() {
        listPage.open(baseUrl);
        listPage.clickNovo();
        formPage.fillForm("João Silva", "12345678901");
        formPage.submit();
        // voltar à listagem
        listPage.open(baseUrl);
        List<String> nomes = listPage.listarNomes();
        Assertions.assertTrue(nomes.contains("João Silva"));
        // excluir
        String id = ((String) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return JSON.parse(localStorage.getItem('funcs'))[0].id"));
        listPage.clickExcluirById(id);
        // confirm via JS alert in demo code triggers browser confirm -> we already handled confirm with native confirm
        // no need to accept because demo uses confirm() and then alert('Excluído'); for demo, adjust if needed
        // Recarrega e confere exclusão
        listPage.open(baseUrl);
        Assertions.assertFalse(listPage.listarNomes().contains("João Silva"));
    }


    @Test
    public void testeValidacaoCpfInvalido() {
        listPage.open(baseUrl);
        listPage.clickNovo();
        formPage.fillForm("Nome X", "abc"); // inválido
        formPage.submit();
        // Como form.html usa required + pattern, o navegador normalmente bloqueia o submit.
        // Verificamos se ainda estamos na página do form (id vazio).
        Assertions.assertTrue(driver.getCurrentUrl().contains("form.html"));
    }
}
