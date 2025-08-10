package ru.praktikum.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By yesButton = By.xpath("//button[text()='Да']");
    private final By orderCompleteHeader = By.xpath("//div[contains(text(),'Заказ оформлен')]");

    public ConfirmationModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 секунд на всякий случай
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompleteHeader));
        System.out.println("Окно 'Заказ оформлен' появилось");
    }

    public boolean isOrderCompleteVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompleteHeader)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}