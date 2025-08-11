package ru.praktikum.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Page Object для первой страницы формы заказа самоката.
public class OrderPageStepOne {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Поле ввода имени
    private final By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    // Поле ввода фамилии
    private final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле ввода адреса
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле для выбора станции метро
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    // Элемент выпадающего списка с вариантами станций метро
    private final By metroOption = By.xpath("//div[@class='Order_Text__2broi']");
    // Поле ввода телефона
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка перехода на следующий шаг оформления заказа
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Конструктор WebDriver
    public OrderPageStepOne(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Вводим имя в соответствующее поле
    public void fillFirstName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).sendKeys(name);
    }
    // Вводим фамилию
    public void fillLastName(String surname) {
        driver.findElement(lastNameField).sendKeys(surname);
    }
    // Вводим адрес
    public void fillAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    // Выьираем станцию метро
    public void selectMetro(String metro) {
        driver.findElement(metroField).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + metro + "']"))).click();
    }
    // Вводим телефон
    public void fillPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    // Переходим на следующий шаг оформления заказа.
    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }
}