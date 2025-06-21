package org.example.test.java.TestPro.MainTestPro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.test.java.TestPro.ProductDeleteTest;
import org.example.test.java.TestPro.ProductEditTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EditPro {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:4200/products");
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
