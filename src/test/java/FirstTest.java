import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FirstTest extends BaseTest {

    @Test
    public void checkTitle() throws InterruptedException {
        driver.get("https://limestore.com/ru_ru/");
        Thread.sleep(8000);
        String expectedTitle = "LIME | Официальный магазин";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle,
                "Заголовок страницы неверный!");

    }

    @Test
    public void checkLocatorsCss() throws InterruptedException {
        driver.get("https://limestore.com/ru_ru/");
        Thread.sleep(8000);

        WebElement logo = driver.findElement(By.id("logo"));
        assertTrue(logo.isDisplayed());

        WebElement searchInput = driver.findElement(By.name("search"));
        assertFalse(searchInput.isDisplayed());

        List<WebElement> iconButtons = driver.findElements(By.className("btn-control"));
        assertEquals(4, iconButtons.size());

        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        assertTrue(buttons.size() > 1);

        WebElement menu = driver.findElement(By.cssSelector("#logo .hamburger-menu.burger"));
        menu.click();

        Thread.sleep(1000);
    }
}