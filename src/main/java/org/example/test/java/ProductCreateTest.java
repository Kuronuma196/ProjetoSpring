package org.example.test.java;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCreateTest extends BaseTest {

    @Test
    public void testCreateProductSuccessfully() {
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-crud/button")).click(); // botão Novo Produto

        driver.findElement(By.id("proNome")).sendKeys("Produto Teste");
        driver.findElement(By.id("proPrecoCusto")).sendKeys("99.99");
        driver.findElement(By.id("proPrecoVenda")).sendKeys("99.99");
        driver.findElement(By.id("quantidadeEstoque")).sendKeys("100");
        driver.findElement(By.id("categoria")).sendKeys("Testes");
        driver.findElement(By.id("codigoBarras")).sendKeys("9876543210123");
        driver.findElement(By.id("marca")).sendKeys("Marca Teste");
        driver.findElement(By.id("unidadeMedida")).sendKeys("Unidade");
        driver.findElement(By.id("ativo")).click(); // Se for checkbox
        driver.findElement(By.id("dataCadastro")).sendKeys("2025-05-31");
        driver.findElement(By.id("dataAtualizacao")).sendKeys("2025-05-31");

        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/button[1]")).click(); // botão Salvar

        // Verificação básica
        WebElement created = driver.findElement(By.xpath("//*[contains(text(), 'Produto Teste')]"));
        assert(created.isDisplayed());
    }

    @Test
    public void testCreateProductWithEmptyFields() {
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-crud/button")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/button[1]")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}