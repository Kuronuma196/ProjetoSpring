package TestPag.MainTestPag;


import TestPag.PagamentoEditTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class EditPag {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:4200/formaPagamento/update/1");
            driver.manage().window().maximize();

            // Aguarda carregamento (melhor usar WebDriverWait no futuro)
            Thread.sleep(2000);

           PagamentoEditTest pagamentoEditTest = new PagamentoEditTest();
            pagamentoEditTest.testEditPagamentoSuccessfully();
            pagamentoEditTest.testEditPagamentoWithEmptyFields();

        } catch (Exception e) {
            System.out.println(" Erro no teste: " + e.getMessage());
        } finally {
            driver.quit();
        }


    }
}
