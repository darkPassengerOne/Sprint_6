package ru.praktikum.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private final By orderCompleteHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");

    // Конструктор WebDriver
    public ConfirmationModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Подтверждаем заказ в модальном окне.
    public void confirmOrder() {
        WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(yesButton));
        yesBtn.click();
        System.out.println("Клик на кнопку 'Да' выполнен");

        wait.until(ExpectedConditions.visibilityOfElementLocated(orderCompleteHeader));
        System.out.println("Окно 'Заказ оформлен' появилось");
    }

    // Проверяем виден ли заголовок "Заказ оформлен"
    public boolean isOrderCompleteVisible() {
        return !driver.findElements(orderCompleteHeader).isEmpty();
    }
}
