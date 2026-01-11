import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Test
    public void checkImplicitWait(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://limestore.com/ru_ru/product/28911_8969_454-temno_krasnyi");
        WebElement title = driver.findElement(By.xpath("//div[@class='ProductTitlePrice__title']/h1"));
        assertEquals("ПЛАТЬЕ МАКСИ ИЗ 100% ШЕРСТИ", title.getText());
    }

    @Test
    public void checkExplicitWait(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://limestore.com/ru_ru/product/28911_8969_454-temno_krasnyi");
        wait.until(ExpectedConditions.titleContains("Платье макси из 100% шерсти темно-красный цвет - LIMÉ"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ProductTitlePrice__title']/h1")
        ));

        WebElement menuOpenButton = driver.findElement(By.cssSelector("div.hamburger-menu.burger"));
        menuOpenButton.click();

        WebElement parfumMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//span[@class='mainmenu__kind' and text()='Парфюмерия']")
        ));
        assertNotNull(parfumMenu);
        parfumMenu.click();

        WebElement menuLink = driver.findElement(By.xpath("//a[@class='mainmenu__link']/span"));
        wait.until(ExpectedConditions.textToBePresentInElement(menuLink, "ЖЕНСКИЕ АРОМАТЫ"));
    }

    @Test
    public void checkFluentWait() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        driver.get("https://limestore.com/ru_ru/product/28911_8969_454-temno_krasnyi");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ProductTitlePrice__title']/h1")
        ));
    }
}