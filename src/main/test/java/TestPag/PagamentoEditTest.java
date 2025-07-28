package TestPag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class PagamentoEditTest extends BaseTest1 {

    public void testEditPagamentoSuccessfully() {
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-crud/app-pagamento-read/div/table/tbody/tr/td[9]/a[1]/i")).click();

        WebElement nome = driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-update/mat-card/form/mat-form-field[1]/div[1]/div/div/input"));

        nome.clear();
        nome.sendKeys("Pagamento Editado");

        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-update/mat-card/div[2]/button[1]")).click();

        WebElement updated = driver.findElement(By.xpath("//*[contains(text(), 'Pagamento Editado')]"));
        assert(updated.isDisplayed());
    }


    public void testEditPagamentoWithEmptyFields() {
        driver.findElement(By.cssSelector("body > app-root > app-nav > mat-sidenav-container > mat-sidenav-content > app-pagamento-crud > app-pagamento-read > div > table > tbody > tr > td.mat-mdc-cell.mdc-data-table__cell.cdk-cell.cdk-column-action.mat-column-action.ng-star-inserted > a.edit > i")).click();

        driver.findElement(By.id("fpgNome")).clear();
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-pagamento-update/mat-card/div[2]/button[1]")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}
