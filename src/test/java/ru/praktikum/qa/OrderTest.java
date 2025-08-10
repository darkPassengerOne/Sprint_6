package ru.praktikum.qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {

    @ParameterizedTest
    @CsvSource({
            // браузер, имя, фамилия, адрес, метро, телефон, дата, срок аренды, комментарий
            "chrome, Иван, Иванов, Москва, Бульвар Рокоссовского, 89999999999, 10.08.2025, сутки, коммент 1",
            "firefox, Петр, Петров, Санкт-Петербург, Лубянка, 88888888888, 11.08.2025, двое суток, коммент 2"
    })
    public void testOrderFlow(String browser, String firstName, String lastName, String address, String metro, String phone,
                              String date, String period, String comment) {

        WebDriver driver;

        // Выбор браузера
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }

        try {
            driver.get("https://qa-scooter.praktikum-services.ru/");

            MainPage mainPage = new MainPage(driver);
            mainPage.closeCookieBanner();
            mainPage.clickTopOrderButton();

            OrderPageStepOne stepOne = new OrderPageStepOne(driver);
            stepOne.fillFirstName(firstName);
            stepOne.fillLastName(lastName);
            stepOne.fillAddress(address);
            stepOne.selectMetro(metro);
            stepOne.fillPhone(phone);
            stepOne.clickNext();

            OrderPageStepTwo stepTwo = new OrderPageStepTwo(driver);
            stepTwo.setDate(date);
            stepTwo.chooseRentalPeriod(period);
            stepTwo.selectColorBlack();
            stepTwo.setComment(comment);
            stepTwo.clickOrder();

            ConfirmationModal modal = new ConfirmationModal(driver);
            modal.confirmOrder();

            assertTrue(modal.isOrderCompleteVisible(), "Заказ не был оформлен");

        } finally {
            driver.quit();
        }
    }
}