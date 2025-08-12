package PageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

class OrderingScooterTest {
    DriversCreating driversCreating = new DriversCreating();
    //private WebDriver driver = driversCreating.getFirefoxDriver();
    private WebDriver driver = driversCreating.getChromeDriver();
    WaitHelper wh = new WaitHelper(driver);

    @ParameterizedTest
    @CsvSource({
            "Малютка, Охотница, ул. Котиков 3, 89009877890, Бульвар Рокоссовского, 7, пятеро суток, чёрный жемчуг, Без комментариев",
            "Сон, Кихун, Ленина 10, 89991456567, Черкизовская, 15, семеро суток, серая безысходность, Важный заказ"
    })
    //Старт заказа с верхней кнопки Заказать с одним набором данных
    public void scooterOrderTextsStartWithTopButton(String name, String surname, String address, String phone, String metro, int date, String period, String color, String comment) {
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //создаем объект
        OrderingScooter orderingScooter = new OrderingScooter(driver);
        //Закрываем куки баннер
        CookieBanner cookieBanner = new CookieBanner(driver);
        cookieBanner.cookieBannerClose();
        //нажимаем верхнюю кнопку Заказать
        By topButton = orderingScooter.getTopOrderButton();
        //Запускаем два шага/метода заполнения страниц заказа
        orderingScooter.orderFirstPage(topButton, name, surname, address, phone, metro);
        orderingScooter.orderSecondPage(date, period, color, comment);
        //нажимаем кнопку Заказать на финальной странице
        By bottomButton = orderingScooter.getBottomOrderButton();
        orderingScooter.orderButtonClick(bottomButton);
        //Завершаем заказ
        orderingScooter.yesButtonClick();
        //Получаем текст в всплывающем окне успешного заказа
        String actual = orderingScooter.successfulWindowGetText();
        //Проверяем есть текст сообщение об успешном создании заказа
        assertTrue(actual.contains("Заказ оформлен"));
    }

    @ParameterizedTest
    @CsvSource({
            "Дейнерис, Бурерожденная, ул. Драконов 3, 89009834590, Лубянка, 1, сутки, чёрный жемчуг, Без комментариев",
            "Анна, Болейн, Сокора 10, 89209456567, Чистые пруды, 28, двое суток, серая безысходность, Важный заказ"
    })
    //Старт заказа с нижней кнопки Заказать с другим набором данных
    public void scooterOrderTextsStartWithBottomButton(String name, String surname, String address, String phone, String metro, int date, String period, String color, String comment) {
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //создаем объект
        OrderingScooter orderingScooter = new OrderingScooter(driver);
        //Закрываем куки баннер
        CookieBanner cookieBanner = new CookieBanner(driver);
        cookieBanner.cookieBannerClose();

        // Получаем нижнюю кнопку Заказать
        By bottomButton = orderingScooter.getBottomOrderButton();

        // Ждём и прокручиваем к ней
        WebElement buttonElement = wh.waitForElementToBeClickable(bottomButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", buttonElement);

        //Запускаем два шага/метода заполнения страниц заказа
        orderingScooter.orderFirstPage(bottomButton, name, surname, address, phone, metro);
        orderingScooter.orderSecondPage(date, period, color, comment);
        //нажимаем кнопку Заказать на финальной странице
        orderingScooter.orderButtonClick(bottomButton);
        //Завершаем заказ
        orderingScooter.yesButtonClick();
        //Получаем текст в всплывающем окне успешного заказа и проверяем
        String actual = orderingScooter.successfulWindowGetText();
        assertTrue(actual.contains("Заказ оформлен"));
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}


