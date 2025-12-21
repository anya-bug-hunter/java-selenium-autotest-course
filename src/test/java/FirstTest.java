import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class FirstTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void checkTitle() throws InterruptedException {
        driver.get("https://limestore.com/ru_ru/");
        Thread.sleep(8000);
        String expectedTitle = "LIME | Официальный магазин";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle,
                "Заголовок страницы неверный!");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}