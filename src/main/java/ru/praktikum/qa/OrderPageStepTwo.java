package ru.praktikum.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Page Object для второй страницы формы заказа самоката.
public class OrderPageStepTwo {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Поле ввода даты доставки самоката
    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Выпадающий список выбора срока аренды
    private final By rentalPeriodDropdown = By.className("Dropdown-control");
    // Чекбокс выбора чёрного цвета самоката
    private final By blackColorCheckbox = By.id("black");
    // Поле ввода комментария для курьера
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Конструктор WebDriver
    public OrderPageStepTwo(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Устанавливаем дату доставки самоката.
    public void setDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);
    }

    // Выбираем срок аренды в выпадающем списке.
    public void chooseRentalPeriod(String period) {
        driver.findElement(rentalPeriodDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='Dropdown-option' and text()='" + period + "']"))).click();
    }

    // Выбираем чёрный цвет самоката.
    public void selectColorBlack() {
        driver.findElement(blackColorCheckbox).click();
    }
    // Заполняем комментарий для курьера.
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    // Кликаем по кнопке "Заказать" для отправки формы.
    public void clickOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    // Подтверждаем оформление заказа в модальном окне.
    public void confirmOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ждем и кликаем кнопку "Да"
        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Да']")));
        yesButton.click();
        System.out.println("Клик на кнопку 'Да' выполнен");

        // Ждем появления окна "Заказ оформлен"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]")
        ));
        System.out.println("Окно 'Заказ оформлен' появилось");
    }
}