package TestPag.MainTestPag;


import TestPag.PagamentoDeleteTest;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class DeletePag {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:4200/formaPagamento/delete/1");
            driver.manage().window().maximize();

            // Aguarda carregamento (melhor usar WebDriverWait no futuro)
            Thread.sleep(2000);

            PagamentoDeleteTest pagamentoDeleteTest = new PagamentoDeleteTest();
            pagamentoDeleteTest.testDeletePagamentoSuccessfully();
            pagamentoDeleteTest.testDeleteWithoutSelectingAnything();

        } catch (Exception e) {
            System.out.println(" Erro no teste: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
