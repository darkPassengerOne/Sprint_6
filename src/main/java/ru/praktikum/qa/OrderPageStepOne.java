package ru.praktikum.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageStepOne {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By metroOption = By.xpath("//div[@class='Order_Text__2broi']");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    public OrderPageStepOne(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void fillFirstName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).sendKeys(name);
    }

    public void fillLastName(String surname) {
        driver.findElement(lastNameField).sendKeys(surname);
    }

    public void fillAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void selectMetro(String metro) {
        driver.findElement(metroField).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + metro + "']"))).click();
    }

    public void fillPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }
}