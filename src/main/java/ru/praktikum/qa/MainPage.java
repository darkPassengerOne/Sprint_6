package ru.praktikum.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Page Object для главной страницы.
public class MainPage {

    private final WebDriver driver;

    // Локатор заголовков вопросов FAQ (нажимаем на заголовок, чтобы раскрыть/скрыть ответ)
    private final String faqQuestionsXpath = "//div[@data-accordion-component='AccordionItemHeading']";
    // Локатор кнопки "Принять cookies" в cookie-баннере
    private final String cookieBannerButtonId = "rcc-confirm-button";
    // Локатор кнопки "Заказать" в хедере
    private final By topOrderButton = By.xpath("//button[text()='Заказать' and ancestor::div[contains(@class,'Header')]]");

    // Конструктор, принимающий WebDriver
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Кликает по кнопке "Заказать" в верхней части страницы.
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }
    // Возвращает текст ответа на вопрос FAQ по его индексу.
    public String getFaqAnswerText(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // XPath для вопроса с индексом (нумерация с 1)
        String questionXpath = "(" + faqQuestionsXpath + ")[" + (index + 1) + "]";

        // Кликаем по вопросу, чтобы раскрыть ответ
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(questionXpath))).click();

        // XPath для ответа — следующий соседний элемент после вопроса
        String answerXpath = questionXpath + "/following-sibling::div[@data-accordion-component='AccordionItemPanel']";

        // Ждем появления текста ответа
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answerXpath)));

        return answer.getText().trim();
    }
    // Закрывает cookie-баннер
    public void closeCookieBanner() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            By cookieBannerButton = By.id(cookieBannerButtonId);

            // Ожидаем появления кнопки закрытия баннера
            WebElement cookieButton = wait.until(ExpectedConditions.presenceOfElementLocated(cookieBannerButton));
            if (cookieButton.isDisplayed()) {
                cookieButton.click();
            }
        } catch (TimeoutException | NoSuchElementException ignored) {
            // баннера нет — пропускаем
        }
    }
}