package org.example.test.java;

import org.example.test.java.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDeleteTest extends BaseTest {

    @Test
    public void testDeleteProductSuccessfully() {
        driver.findElement(By.cssSelector(".btn-delete")).click();
        driver.findElement(By.id("btn-confirmar-exclusao")).click();

        // Verifica se o produto sumiu
        boolean produtoExcluido = driver.findElements(By.xpath("//*[contains(text(), 'Produto Teste')]")).isEmpty();
        assert(produtoExcluido);
    }

    @Test
    public void testDeleteWithoutSelectingAnything() {
        // Dependendo da interface, pode haver verificação se não há item selecionado
        driver.findElement(By.cssSelector(".btn-delete")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}