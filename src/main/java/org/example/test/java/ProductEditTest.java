package org.example.test.java;

import org.example.test.java.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductEditTest extends BaseTest {

    @Test
    public void testEditProductSuccessfully() {
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-crud/app-product-read/div/table/tbody/tr/td[13]/a[1]/i")).click();

        WebElement nome = driver.findElement(By.id("proNome"));
        nome.clear();
        nome.sendKeys("Produto Editado");

        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-update/mat-card/button[1]")).click();

        WebElement updated = driver.findElement(By.xpath("//*[contains(text(), 'Produto Editado')]"));
        assert(updated.isDisplayed());
    }

    @Test
    public void testEditProductWithEmptyFields() {
        driver.findElement(By.cssSelector("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-crud/app-product-read/div/table/tbody/tr/td[13]/a[1]/i")).click();

        driver.findElement(By.id("proNome")).clear();
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-update/mat-card/button[1]")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}