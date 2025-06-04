package org.example.test.java;

import org.example.test.java.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDeleteTest extends BaseTest {

    @Test
    public void testDeleteProductSuccessfully() {
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-crud/app-product-read/div/table/tbody/tr/td[13]/a[2]/i")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-delete/mat-card/button[1]")).click();

        // Verifica se o produto sumiu
        boolean produtoExcluido = driver.findElements(By.xpath("//*[contains(text(), 'Produto Teste')]")).isEmpty();
        assert(produtoExcluido);
    }

    @Test
    public void testDeleteWithoutSelectingAnything() {
        // Dependendo da interface, pode haver verificação se não há item selecionado
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-crud/app-product-read/div/table/tbody/tr/td[2]")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}