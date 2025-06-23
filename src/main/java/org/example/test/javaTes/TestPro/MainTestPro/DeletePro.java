package org.example.test.javaTes.TestPro.MainTestPro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.test.javaTes.TestPro.ProductDeleteTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeletePro {
    public static void main(String[] args) {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    try {
        driver.get("http://localhost:4200/products/delete/1");
        driver.manage().window().maximize();

        // Aguarda carregamento (melhor usar WebDriverWait no futuro)
        Thread.sleep(2000);

        ProductDeleteTest productDeleteTest = new ProductDeleteTest();
        productDeleteTest.testDeleteProductSuccessfully();
        productDeleteTest.testDeleteWithoutSelectingAnything();

    } catch (Exception e) {
        System.out.println(" Erro no teste: " + e.getMessage());
    } finally {
        driver.quit();
    }
}
}
