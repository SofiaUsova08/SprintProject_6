package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderingScooter {
    private WebDriver driver;
    private WaitHelper wh;

    //Верхняя кнопка Заказать в заголовке
    private By topOrderButton = By.cssSelector(".Header_Header__214zg .Button_Button__ra12g");
    // Геттер для верхней кнопки
    public By getTopOrderButton() {
        return topOrderButton;
    }

    //Нижняя кнопка Заказать
    private By bottomOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    // Геттер для нижней кнопки
    public By getBottomOrderButton() {
        return bottomOrderButton;
    }

    //локатор поля Имя
    private By name = By.cssSelector("input.Input_Input__1iN_Z[placeholder='* Имя']");
    //локатор поля Фамилия
    private By surname = By.cssSelector("input.Input_Input__1iN_Z[placeholder='* Фамилия']");
    //локатор поля Адреса
    private By address = By.cssSelector("input.Input_Input__1iN_Z[placeholder='* Адрес: куда привезти заказ']");
    //локатор поля Станции метро
    private By metroStations = By.cssSelector(".select-search__input[placeholder='* Станция метро']");
    //локатор поля Телефона
    private By phoneNumber = By.cssSelector("input.Input_Input__1iN_Z[placeholder='* Телефон: на него позвонит курьер']");
    // локатор кнопки Далее
    private By nextButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    //поля выбора даты, когда доставить самокат
    private By dateBringScooter = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // локатор поля срок аренды
    private By rentalPeriod = By.cssSelector(".Dropdown-placeholder");
    //локатор поля Комментарий для курьера
    private By commentForCourier = By.cssSelector("input.Input_Input__1iN_Z[placeholder='Комментарий для курьера']");
    // локатор кнопки Да в окне Хотите оформить заказ
    private By yesButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    // локатор всплывающего окна с сообщением об успешном создании заказа
    private By successfulWindow = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");


    //конструктор класса
    public OrderingScooter(WebDriver driver) {
        this.driver = driver;
        this.wh = new WaitHelper(driver);
    }

    //метод нажатия на любую из кнопок Заказать
    public void orderButtonClick(By button) {
        driver.findElement(button).click();
    }
    //метод заполнения поля Имя
    public void setName(String n){
        driver.findElement(name).sendKeys(n);
    }
    //метод заполнения поля Фамилия
    public void setSurname(String s){
        driver.findElement(surname).sendKeys(s);
    }
    //метод заполнения поля Адреса
    public void setAddress(String a){
        driver.findElement(address).sendKeys(a);
    }
    //метод клика на поле Станции метро для раскрытия выпадающего списка
    public void setMetroStations(){
        driver.findElement(metroStations).click();
    }
    //метод выбора Станции метро из выпадающего списка
    public void selectMetroStation(String stationName) {
        //локатор для выбора Станции метро из выпалаюшего списка
        By stationLocator = By.xpath("//div[@class='Order_Text__2broi' and text()='" + stationName + "']");
        // Ждём появления выпадающего списка станций и кликаем
        WebElement stationElement = wh.waitForElementToBeClickable(stationLocator);
        stationElement.click();
    }
    //метод заполнения поля Номера телефона
    public void setPhoneNumber(String pn){
        driver.findElement(phoneNumber).sendKeys(pn);
    }
    //метод нажатия на кнопку Далее
    public void nextButtonClick(){
        driver.findElement(nextButton).click();
    }
    //ШАГ 1. Общий метод для заполнение полей первой страницы заказа
    public void orderFirstPage(By button, String n, String s, String a, String pn, String stationName) {
        orderButtonClick(button);
        setName(n);
        setSurname(s);
        setAddress(a);
        setPhoneNumber(pn);
        setMetroStations();
        selectMetroStation(stationName);
        nextButtonClick();
    }

    //метод нажатия на поле выбора даты, когда привезти самокат
    public void dateBringScooterClick(){
        wh.waitForElementToBeClickable(dateBringScooter);
        driver.findElement(dateBringScooter).click();
    }

    //метод выбора дня, когда привезти самокат
    public void selectDateBringScooter(int date) {
        //локатор для выбора дня, когда привезти самокат
        By dateLocator = By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='" + date + "']");
        // Ждём появления календаря и кликаем
        WebElement dateElement = wh.waitForElementToBeClickable(dateLocator);
        dateElement.click();
    }

    //метод клика на поле Срока аренды
    public void setRentalPeriod(){
        driver.findElement(rentalPeriod).click();
    }

    //метод выбора Срока аренды
    public void selectRentalPeriod(String period) {
        // локатор для выбора срока аренды из выпалаюшего списка
        By rentalPeriodSelect = By.xpath("//div[@class='Dropdown-option' and text()='" + period + "']");
        // Ждём появления списка и кликаем
        WebElement dateElement = wh.waitForElementToBeClickable(rentalPeriodSelect);
        dateElement.click();
    }

    //метод выбора Цвета самоката
    public void selectScooterColor(String color) {
        By checkboxScooterColor = By.xpath("//div[@class='Order_Checkboxes__3lWSI']");
        if("чёрный жемчуг".equals(color)) {
            checkboxScooterColor = By.id("black");
        } else if("серая безысходность".equals(color)) {
            checkboxScooterColor = By.id("grey");
        }
        driver.findElement(checkboxScooterColor).click();
    }

    //метод для заполнения поля Комментрария для курьера
    public void setCommentForCourier(String comment){
        driver.findElement(commentForCourier).sendKeys(comment);
    }

    // ШАГ 2. Общий метод для заполнение полей второй страницы заказа
    public void orderSecondPage(int date, String period, String color, String comment) {
        dateBringScooterClick();
        selectDateBringScooter(date);
        setRentalPeriod();
        selectRentalPeriod(period);
        selectScooterColor(color);
        setCommentForCourier(comment);
    }

    //метод клика на кнопку Да в окне Хотите оформить заказ
    public void  yesButtonClick(){
        driver.findElement(yesButton).click();
    }

    //метод для получения текста в всплывающем окне успешного заказа
    public String successfulWindowGetText() {
        String text = driver.findElement(successfulWindow).getText();
        return text;
    }

}
