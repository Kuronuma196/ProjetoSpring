package org.example.test.javaTes.TestPro.MainTestPro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.test.javaTes.TestPro.ProductEditTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EditPro {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://http://localhost:4200/products/update/1");
            driver.manage().window().maximize();

            // Aguarda carregamento (melhor usar WebDriverWait no futuro)
            Thread.sleep(2000);

            ProductEditTest productEditTest = new ProductEditTest();
            productEditTest.testEditProductSuccessfully();
            productEditTest.testEditProductWithEmptyFields();

        } catch (Exception e) {
            System.out.println(" Erro no teste: " + e.getMessage());
        } finally {
            driver.quit();
        }


    }
}
