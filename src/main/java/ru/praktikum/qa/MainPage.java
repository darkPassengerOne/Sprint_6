package ru.praktikum.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    // Храню XPath как строки, чтобы проще подставлять индексы
    private final String faqQuestionsXpath = "//div[@data-accordion-component='AccordionItemHeading']";
    private final String cookieBannerButtonId = "rcc-confirm-button";
    private final By topOrderButton = By.xpath("//button[text()='Заказать' and ancestor::div[contains(@class,'Header')]]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public String getFaqAnswerText(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // XPath для вопроса с индексом (нумерация с 1)
        String questionXpath = "(" + faqQuestionsXpath + ")[" + (index + 1) + "]";

        // Кликаем по вопросу, чтобы раскрыть ответ
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(questionXpath))).click();

        // XPath для ответа — следующий sibling после вопроса
        String answerXpath = questionXpath + "/following-sibling::div[@data-accordion-component='AccordionItemPanel']";

        // Ждем появления текста ответа
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answerXpath)));

        return answer.getText().trim();
    }

    public void closeCookieBanner() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            By cookieBannerButton = By.id(cookieBannerButtonId);
            WebElement cookieButton = wait.until(ExpectedConditions.presenceOfElementLocated(cookieBannerButton));
            if (cookieButton.isDisplayed()) {
                cookieButton.click();
            }
        } catch (TimeoutException | NoSuchElementException ignored) {
            // баннера нет — пропускаем
        }
    }
}