package TestPag;

import TestPag.BaseTest1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class PagamentoDeleteTest extends BaseTest1 {

    public void testDeletePagamentoSuccessfully() {
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-crud/app-pagamento-read/div/table/tbody/tr/td[9]/a[2]/i")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-delete/mat-card/div[2]/button[1]")).click();

        // Verifica se o produto sumiu
        boolean produtoExcluido = driver.findElements(By.xpath("//*[contains(text(), 'Pagamento Teste')]")).isEmpty();
        assert(produtoExcluido);
    }


    public void testDeleteWithoutSelectingAnything() {
        // Dependendo da interface, pode haver verificação se não há item selecionado
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-crud/app-pagamento-read/div/table/tbody/tr/td[1]")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}