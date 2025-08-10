package PageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ImportantQuestionsDropdownTest {
    DriversCreating driversCreating = new DriversCreating();
    //private WebDriver driver = driversCreating.getFirefoxDriver();
    private WebDriver driver = driversCreating.getChromeDriver();


    @Test
    public void importantQuestionsDropdownCheckTexts() {
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //создаем объект
        ImportantQuestionsDropdown iqd = new ImportantQuestionsDropdown(driver);
        // скролим до проверяемого блока
        WebElement element = driver.findElement(By.className("accordion"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        //Закрываем куки баннер
        CookieBanner cookieBanner = new CookieBanner(driver);
        cookieBanner.cookieBannerClose();

        //получаем из класса список с ожидаемыми текстами
        ArrayList<String> expected = iqd.getExpectedTexts();
        //получаем актуальный список
        ArrayList<String> actual = iqd.accordionHeadingClickAndGetAllTexts(iqd.accordionHeadings, iqd.accordionPanels);

        //Проверяем
        assertAll(
                () -> assertEquals(expected.get(0), actual.get(0)),
                () -> assertEquals(expected.get(1), actual.get(1)),
                () -> assertEquals(expected.get(2), actual.get(2)),
                () -> assertEquals(expected.get(3), actual.get(3)),
                () -> assertEquals(expected.get(4), actual.get(4)),
                () -> assertEquals(expected.get(5), actual.get(5)),
                () -> assertEquals(expected.get(6), actual.get(6)),
                () -> assertEquals(expected.get(7), actual.get(7))
        );
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}


