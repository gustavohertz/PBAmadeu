package com.yourcompany.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioListPage extends BasePage {
    private By rows = By.cssSelector("#lista tr");
    private By novoBtn = By.cssSelector("a[href='#/novo'], a[href='form.html#/novo']");

    public FuncionarioListPage(WebDriver driver) { super(driver); }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/index.html#/");
    }

    public void clickNovo() {
        driver.findElement(novoBtn).click();
    }

    public List<String> listarNomes() {
        List<WebElement> elements = driver.findElements(By.cssSelector("#lista tr td:nth-child(2)"));
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickExcluirById(String id) {
        WebElement btn = driver.findElement(By.cssSelector("button[data-id='" + id + "']"));
        btn.click();
    }

    public void confirmAlert() {
        driver.switchTo().alert().accept();
    }
}
