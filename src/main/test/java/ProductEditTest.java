public class ProductEditTest extends BaseTest {

    @Test
    public void testEditProductSuccessfully() {
        driver.findElement(By.cssSelector(".btn-edit")).click();

        WebElement nome = driver.findElement(By.id("nome"));
        nome.clear();
        nome.sendKeys("Produto Editado");

        driver.findElement(By.id("btn-atualizar")).click();

        WebElement updated = driver.findElement(By.xpath("//*[contains(text(), 'Produto Editado')]"));
        assert(updated.isDisplayed());
    }

    @Test
    public void testEditProductWithEmptyFields() {
        driver.findElement(By.cssSelector(".btn-edit")).click();

        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("btn-atualizar")).click();

        WebElement errorMsg = driver.findElement(By.className("error-msg"));
        assert(errorMsg.isDisplayed());
    }
}