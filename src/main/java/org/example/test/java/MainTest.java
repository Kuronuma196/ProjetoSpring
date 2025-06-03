package org.example.test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Abre o sistema
            driver.get("http://localhost:4200/");
            driver.manage().window().maximize();

            // Aguarda carregamento (melhor usar WebDriverWait no futuro)
            Thread.sleep(2000);

            // Clica no botão "Novo Produto"
            WebElement btnNovoProduto = driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-crud/button"));
            btnNovoProduto.click();

            Thread.sleep(1000);

            // Preenche os campos do formulário
            driver.findElement(By.id("proId")).sendKeys("123");
            driver.findElement(By.id("proNome")).sendKeys("Produto Teste");
            driver.findElement(By.id("proPrecoCusto")).sendKeys("5.50");
            driver.findElement(By.id("proPrecoVenda")).sendKeys("8.90");
            driver.findElement(By.id("quantidadeEstoque")).sendKeys("100");
            driver.findElement(By.id("categoria")).sendKeys("Testes");
            driver.findElement(By.id("codigoBarras")).sendKeys("9876543210123");
            driver.findElement(By.id("marca")).sendKeys("Marca Teste");
            driver.findElement(By.id("unidadeMedida")).sendKeys("Unidade");
            driver.findElement(By.id("ativo")).click(); // Se for checkbox
            driver.findElement(By.id("dataCadastro")).sendKeys("2025-05-31");
            driver.findElement(By.id("dataAtualizacao")).sendKeys("2025-05-31");

            Thread.sleep(1000);

            // Clica no botão "Salvar"
            WebElement btnSalvar = driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/button[1]"));
            btnSalvar.click();

            System.out.println(" Produto criado com sucesso!");

        } catch (Exception e) {
            System.out.println(" Erro no teste: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
