package TestPag;

import TestPag.BaseTest1;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class PagamentoCreateTest extends BaseTest1 {

    @Test
    public void testCreatePagamentoSuccessfully() {
        WebElement btnNovoPagamento = driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-crud/button"));
        btnNovoPagamento.click();

        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-create/mat-card/form/mat-form-field[1]/div[1]/div[2]/div/input")).sendKeys("Pagamento Teste");
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-create/mat-card/form/mat-form-field[2]/div[1]/div[2]/div/input")).sendKeys("5.50");
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-create/mat-card/form/mat-form-field[3]/div[1]/div[2]/div/input")).sendKeys("Deve-se ao Jorge");
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-create/mat-card/form/mat-form-field[5]/div[1]/div[2]/div/input")).sendKeys("sim");
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-create/mat-card/form/mat-form-field[7]/div[1]/div[2]/div/input")).sendKeys("12");
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-create/mat-card/form/mat-form-field[6]/div[1]/div[2]/div/input")).sendKeys("2");
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-create/mat-card/form/mat-form-field[4]/div[1]/div[2]/div/mat-select/div/div[1]")).sendKeys("ativo"); // Se for checkbox


        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/button[1]")).click(); // botão Salvar

        // Verificação básica
        WebElement created = driver.findElement(By.xpath("//*[contains(text(), 'Pagamento Teste')]"));
        assert(created.isDisplayed());
    }

    @Test
    public void testCreatePagamentoWithEmptyFields() {
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-create/mat-card/div[2]/button[1]")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-create/mat-card/div[2]/button[2]")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}
