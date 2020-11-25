import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebDriverLCWaikikiTest {

    private WebDriver driver;
    private Actions actionProvider;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup() {
        driver = new ChromeDriver();
        actionProvider = new Actions(driver);
    }

    @Test
    public void addToWishlistTest() {
        driver.get("https://www.lcwaikiki.by/ru-RU/BY");
        WebElement menuItem = driver.findElement(By.id("menu_1"));
        actionProvider.moveToElement(menuItem).build().perform();
        WebElement jumperMenuItem = driver.findElement(By.xpath("//ul[@id=\"dd_mmm_1_3\"]/li[7]"));
        jumperMenuItem.click();
        WebElement goToSelectedModel = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("model_1038186_4778926")));
        goToSelectedModel.click();
        WebElement addSelectedModelToWishlist = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("add-to-favorite-detail")));
        addSelectedModelToWishlist.click();
        WebElement goToWishlistButton = driver.findElement(By.className("header-favorite-icon"));
        goToWishlistButton.click();
        List <WebElement> favoriteItemsList = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("favorite-item")));
        Assert.assertTrue(favoriteItemsList.size()>0, "Favorite list is empty");
    }

    @AfterMethod(alwaysRun = true)
    public void driverTearDown() {
        driver.quit();
        driver = null;
    }
}
