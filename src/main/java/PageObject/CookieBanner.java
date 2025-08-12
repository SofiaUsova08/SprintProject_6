package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// класс для работы с куки-баннером
public class CookieBanner {
    private WebDriver driver;

    public CookieBanner(WebDriver driver) {
        this.driver = driver;
    }
    //локатор для куки
    private By cookieButton = By.id("rcc-confirm-button");
    // метод который закрывает куки баннер
    public void cookieBannerClose() {
        driver.findElement(cookieButton).click();
    }
}
