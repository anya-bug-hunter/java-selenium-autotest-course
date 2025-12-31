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

    @Test
    public void checkXpath() throws InterruptedException {
        driver.get("https://limestore.com/ru_ru/product/28911_8969_454-temno_krasnyi");
        Thread.sleep(8000);

        WebElement title = driver.findElement(By.xpath("//div[@class='ProductTitlePrice__title']/h1"));
        assertEquals("ПЛАТЬЕ МАКСИ ИЗ 100% ШЕРСТИ", title.getText());

        List<WebElement> colors = driver.findElements(By.xpath("//div[@class='ColorSelector__items']/child::div"));
        assertEquals(2, colors.size());

        WebElement buttonCart = driver.findElement(By.xpath("//span[normalize-space(text())='Добавить в корзину']"));
        assertTrue(buttonCart.isDisplayed());

        WebElement fitBlock = driver.findElement(By.xpath("//div[contains(@class, 'text--gray') and position()=last()]"));
        assertTrue(fitBlock.isDisplayed());
    }
}