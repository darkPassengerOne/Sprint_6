package ru.praktikum.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Page Object для модального окна подтверждения заказа.
public class ConfirmationModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локатор кнопки "Да" в окне подтверждения заказа
    private final By yesButton = By.xpath("//button[text()='Да']");

    // Локатор заголовка с текстом "Заказ оформлен" (отображается после успешного оформления)
    private final By orderCompleteHeader = By.xpath("//div[contains(text(),'Заказ оформлен')]");

    // Конструктор WebDriver
    public ConfirmationModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 секунд на всякий случай
    }

    // Подтверждаем заказ в модальном окне.
    public void confirmOrder() {
        // Ждем, пока кнопка "Да" станет кликабельной, и нажимаем
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
        // Ждем появления заголовка "Заказ оформлен"
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompleteHeader));
        System.out.println("Окно 'Заказ оформлен' появилось");
    }

    // Проверяем виден ли заголовок "Заказ оформлен" true — если заголовок появился, false — если нет или истек таймаут
    public boolean isOrderCompleteVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompleteHeader)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}