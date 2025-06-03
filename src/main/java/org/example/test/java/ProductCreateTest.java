package org.example.test.java;

import org.example.test.java.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCreateTest extends BaseTest {

    @Test
    public void testCreateProductSuccessfully() {
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/button[1]")).click(); // botão Novo Produto

        driver.findElement(By.id("proNome")).sendKeys("Produto Teste");
        driver.findElement(By.id("proPrecoCusto")).sendKeys("99.99");
        driver.findElement(By.id("proPrecoVenda")).sendKeys("99.99");
        driver.findElement(By.id("descricao")).sendKeys("Descrição do produto");

        driver.findElement(By.id("btn-salvar")).click(); // botão Salvar

        // Verificação básica
        WebElement created = driver.findElement(By.xpath("//*[contains(text(), 'Produto Teste')]"));
        assert(created.isDisplayed());
    }

    @Test
    public void testCreateProductWithEmptyFields() {
        driver.findElement(By.id("novo-produto")).click();
        driver.findElement(By.id("btn-salvar")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}