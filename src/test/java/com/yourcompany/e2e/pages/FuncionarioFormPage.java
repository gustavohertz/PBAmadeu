package com.yourcompany.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FuncionarioFormPage extends BasePage {
    private By nome = By.id("nome");
    private By cpf = By.id("cpf");
    private By idField = By.id("id");
    private By submit = By.cssSelector("button[type='submit']");

    public FuncionarioFormPage(WebDriver driver) { super(driver); }

    public void openForNew(String baseUrl) {
        driver.get(baseUrl + "/form.html#/novo");
    }

    public void openForEdit(String baseUrl, String id) {
        driver.get(baseUrl + "/form.html#/editar/" + id);
    }

    public void fillForm(String nomeVal, String cpfVal) {
        driver.findElement(nome).clear();
        driver.findElement(nome).sendKeys(nomeVal);
        driver.findElement(cpf).clear();
        driver.findElement(cpf).sendKeys(cpfVal);
    }

    public void submit() {
        driver.findElement(submit).click();
    }

    public String getIdValue() {
        return driver.findElement(idField).getAttribute("value");
    }
}
