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

public class LogInTest {
    private WebDriver driver;

    @BeforeEach
    void setup () {
        driver = new ChromeDriver();
    }

    @Test
    void logInTest () {
        driver.get("https://mail.yandex.com");
        WebElement logInButton = driver.findElement(By.xpath("//a[@href='https://passport.yandex.com/auth?retpath=https%3A%2F%2Fmail.yandex.com']"));
        logInButton.click();

        WebElement usernameField = driver.findElement(By.id("passp-field-login"));
        usernameField.sendKeys("v1k.pa");

        WebElement submitUsername = driver.findElement(By.id("passp:sign-in"));
        submitUsername.click();

        WebElement passwordField = driver.findElement(By.id("passp-field-passwd"));
        passwordField.sendKeys("v1k.papass");

        WebElement loginButton = driver.findElement(By.id("passp:sign-in"));
        loginButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("https://mail.yandex.com/"));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Inbox — Yandex Mail";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @AfterEach
    void cleanup () {
        driver.quit();
    }
}
