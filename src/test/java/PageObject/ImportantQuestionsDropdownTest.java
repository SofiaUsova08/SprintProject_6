package PageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImportantQuestionsDropdownTest {

    private WebDriver driver;
    private ImportantQuestionsDropdown iqd;
    private CookieBanner cookieBanner;

    private DriversCreating driversCreating = new DriversCreating();


    @BeforeEach
    public void setUp() {

        driver = driversCreating.getFirefoxDriver();
        //driver = driversCreating.getChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        iqd = new ImportantQuestionsDropdown(driver);
        cookieBanner = new CookieBanner(driver);

        // Прокручиваем к блоку аккордеона
        WebElement accordion = driver.findElement(By.className("accordion"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accordion);

        // Закрываем куки
        cookieBanner.cookieBannerClose();
    }

    @ParameterizedTest
    @CsvSource(delimiterString = "|", value = {
            "0| Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "1| Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "2| Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "3| Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "4| Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "5| Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "6| Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "7| Да, обязательно. Всем самокатов! И Москве, и Московской области."
    })
    public void checkAccordionItem(int index, String expectedText) {
        // Кликаем по заголовку
        iqd.accordionHeadingsClick(iqd.accordionHeadings.get(index));

        // Ждём появления текста
        WaitHelper wh = new WaitHelper(driver);
        wh.waitForElementToBeClickable(iqd.accordionPanels.get(index));

        // Получаем текст панели
        String actualText = iqd.accordionPanelGetText(iqd.accordionPanels.get(index));

        // Проверяем
        assertEquals(expectedText, actualText, "Текст не совпадает");
    }

    @AfterEach
    public void teardown() {
            driver.quit();
    }
}

