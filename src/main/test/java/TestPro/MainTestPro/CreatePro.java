package TestPro.MainTestPro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatePro  {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Abre o sistema
            driver.get("http://localhost:4200/products");
            driver.manage().window().maximize();

            // Aguarda carregamento (melhor usar WebDriverWait no futuro)
            Thread.sleep(2000);

            // Clica no botão "Novo Produto"
            WebElement btnNovoProduto = driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-crud/button"));
            btnNovoProduto.click();

            Thread.sleep(1000);

            // Preenche os campos do formulário
            driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[1]/div[1]/div[2]/div/input")).sendKeys("Produto Teste");
            driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[2]/div[1]/div[2]/div/input")).sendKeys("5.50");
            driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[3]/div[1]/div[2]/div/input")).sendKeys("8.90");
            driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[4]/div[1]/div[2]/div/input")).sendKeys("100");
            driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[5]/div[1]/div[2]/div/input")).sendKeys("Testes");
            driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[6]/div[1]/div[2]/div/input")).sendKeys("9876543210123");
            driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[7]/div[1]/div[2]/div/input")).sendKeys("Marca Teste");
            driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[8]/div[1]/div[2]/div/input")).sendKeys("gramas");
            // 1. Abre o dropdown de seleção
            driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[9]/div[1]")).click();
            Thread.sleep(500); // ou use WebDriverWait, se preferir

            // 2. Seleciona a opção 'Inativo' (ela está no mat-option[1] visivelmente)
            driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/mat-option[1]/span")).click();

            Thread.sleep(1000);

            // Clica no botão "Salvar"
           driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/div[2]/button[2]")).click();


            System.out.println(" Produto criado com sucesso!");

         /*   WebElement btnNovoProduto2 = driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/div[2]/button[2]"));
            btnNovoProduto2.click();



             ProductCreateTest productCreateTest = new ProductCreateTest();
            productCreateTest.testCreateProductSuccessfully();
            productCreateTest.testCreateProductWithEmptyFields();
             */


        } catch (Exception e) {
            System.out.println(" Erro no teste: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
