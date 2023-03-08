package seleniumWebDriver;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static seleniumWebDriver.LocatorsConstants.*;
import static seleniumWebDriver.MainConstants.*;

public class LogInTest {
    private WebDriver driver;

    @BeforeEach
    void setup () {
        driver = new ChromeDriver();
    }

    @Test
    void logInTest () throws InterruptedException {
        driver.get(LINK_MAIN_PAGE);
        WebElement logInButton = driver.findElement(By.xpath(LOCATOR_LOGIN_BUTTON_ON_MAIN_PAGE));
        logInButton.click();

        WebElement usernameField = driver.findElement(By.id(LOCATOR_USERNAME_FIELD));
        usernameField.sendKeys(USERNAME);

        WebElement submitUsername = driver.findElement(By.id(LOCATOR_LOGIN_BUTTON_ON_USERNAME_PAGE));
        submitUsername.click();

        WebElement passwordField = driver.findElement(By.id(LOCATOR_PASSWORD_FIELD));
        passwordField.sendKeys(PASSWORD);

        WebElement loginButton = driver.findElement(By.id(LOCATOR_LOGIN_BUTTON_ON_PASSWORD_PAGE));
        loginButton.click();

//        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(LINK_MAIN_PAGE));
        Thread.sleep(10000); // Fluent Wait

        String actualTitle = driver.getTitle();
        String expectedTitle = "Inbox — Yandex Mail";
        Assert.assertTrue("The title must contain \"Inbox — Yandex Mail\"", actualTitle.contains("Inbox — Yandex Mail"));
    }

    @AfterEach
    void cleanup () {
        driver.quit();
    }
}
