package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Создали page object — класс для выпадающего списока в разделе «Вопросы о важном»
public class ImportantQuestionsDropdown {
    //Добавили поле driver
    private WebDriver driver;

    //локаторы выпадающего списока для заголовков
    private By accordionHeading0 = By.id("accordion__heading-0");
    private By accordionHeading1 = By.id("accordion__heading-1");
    private By accordionHeading2 = By.id("accordion__heading-2");
    private By accordionHeading3 = By.id("accordion__heading-3");
    private By accordionHeading4 = By.id("accordion__heading-4");
    private By accordionHeading5 = By.id("accordion__heading-5");
    private By accordionHeading6 = By.id("accordion__heading-6");
    private By accordionHeading7 = By.id("accordion__heading-7");

    //локаторы списока для получения текста при раскрытии:
    private By accordionPanel0 = By.id("accordion__panel-0");
    private By accordionPanel1 = By.id("accordion__panel-1");
    private By accordionPanel2 = By.id("accordion__panel-2");
    private By accordionPanel3 = By.id("accordion__panel-3");
    private By accordionPanel4 = By.id("accordion__panel-4");
    private By accordionPanel5 = By.id("accordion__panel-5");
    private By accordionPanel6 = By.id("accordion__panel-6");
    private By accordionPanel7 = By.id("accordion__panel-7");

    //Создаем список для хранения локаторов выпадающего списока, делаем его public для использования в методах и тестах:
    public List<By> accordionHeadings = Arrays.asList(
            accordionHeading0,
            accordionHeading1,
            accordionHeading2,
            accordionHeading3,
            accordionHeading4,
            accordionHeading5,
            accordionHeading6,
            accordionHeading7
    );

    //Создаем список для хранения локаторов для получения тестов
    public List<By> accordionPanels = Arrays.asList(
            accordionPanel0,
            accordionPanel1,
            accordionPanel2,
            accordionPanel3,
            accordionPanel4,
            accordionPanel5,
            accordionPanel6,
            accordionPanel7
    );

    //Создаем список ожидаемых текстов здесь и делаем его приватным
    private ArrayList<String> expectedTexts = new ArrayList<>(Arrays.asList(
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    ));

    // Геттер для получения ожидаемых текстов expectedTexts
    public ArrayList<String> getExpectedTexts() {
        return expectedTexts;
    }

    //Добавили конструктор класса, в нем инициализируем driver
    public ImportantQuestionsDropdown(WebDriver driver) {
        this.driver = driver;
    }

    //метод для клика на заголовки списка
    public void accordionHeadingsClick(By heading) {
        driver.findElement(heading).click();
    }

    //метод для получения текста
    public String accordionPanelGetText(By panel) {
        String text = driver.findElement(panel).getText();
        return text;
    }

    //Объединяем два метода в шаг, чтобы сохранить все текста в список для теста
    public ArrayList<String> accordionHeadingClickAndGetAllTexts(List<By> headings, List<By> panels) {
        // переменная для хранения всех текстов
        ArrayList<String> allTexts = new ArrayList<>();
        for (int i = 0; i < headings.size(); i++) {
            By heading = headings.get(i);
            By panel = panels.get(i);
            // Кликаем по заголовку
            accordionHeadingsClick(heading);
            // Ждём, чтобы панель появилась
            WaitHelper wh = new WaitHelper(driver);
            wh.waitForElementToBeClickable(panel);
            // Получаем текст панели
            String text = accordionPanelGetText(panel);
            //добавляем полученный тест в общий список
            allTexts.add(text);
        }
        return allTexts;
    }

}

