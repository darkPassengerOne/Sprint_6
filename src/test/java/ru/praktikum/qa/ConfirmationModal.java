package ru.praktikum.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By yesButton = By.xpath("//button[text()='Да']");
    private final By orderCompleteHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");

    public ConfirmationModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void confirmOrder() {
        WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(yesButton));
        yesBtn.click();
        System.out.println("Клик на кнопку 'Да' выполнен");

        wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompleteHeader));
        System.out.println("Окно 'Заказ оформлен' появилось");
    }

    public boolean isOrderCompleteVisible() {
        return !driver.findElements(orderCompleteHeader).isEmpty();
    }
}
