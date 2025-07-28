package TestPro;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductEditTest extends BaseTest {


    public void testEditProductSuccessfully() {
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-crud/app-product-read/div/table/tbody/tr/td[11]/a[1]/i")).click();

        WebElement nome = driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-create/mat-card/form/mat-form-field[1]/div[1]/div[2]/div/input"));

        nome.clear();
        nome.sendKeys("Produto Editado");

        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-update/mat-card/button[1]")).click();

        WebElement updated = driver.findElement(By.xpath("//*[contains(text(), 'Produto Editado')]"));
        assert(updated.isDisplayed());
    }


    public void testEditProductWithEmptyFields() {
        driver.findElement(By.cssSelector("body > app-root > app-nav > mat-sidenav-container > mat-sidenav-content > app-product-crud > app-product-read > div > table > tbody > tr > td.mat-mdc-cell.mdc-data-table__cell.cdk-cell.cdk-column-action.mat-column-action.ng-star-inserted > a.edit > i")).click();

        driver.findElement(By.id("proNome")).clear();
        driver.findElement(By.xpath("/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-product-update/mat-card/button[1]")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}