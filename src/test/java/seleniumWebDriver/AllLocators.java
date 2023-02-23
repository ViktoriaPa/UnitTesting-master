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

public class AllLocators {
    private WebDriver driver;

    @BeforeEach
    void setup () {
        driver = new ChromeDriver();
        driver.get(LINK_MAIN_PAGE);
        WebElement logInButton = driver.findElement(By.xpath(LOCATOR_LOGIN_BUTTON_ON_MAIN_PAGE));
        logInButton.click();

        driver.findElement(By.id(LOCATOR_USERNAME_FIELD)).sendKeys(USERNAME);
        driver.findElement(By.id(LOCATOR_LOGIN_BUTTON_ON_USERNAME_PAGE)).click();
    }

    @Test
    void testCssSelectorRecognizedCorrect (){
        WebElement passField = driver.findElement(By.cssSelector("#passp-field-passwd"));
        String actualAttribute = passField.getAttribute("placeholder");

        String expectedAttribute = "Enter your password";
        Assert.assertEquals("The placeholder must be \"Enter your password\"", expectedAttribute, actualAttribute);

    }

    @Test
    void testIdSelectorRecognizedCorrect (){
        WebElement logInButton = driver.findElement(By.id("passp:sign-in"));
        String actualAttribute = logInButton.getAttribute("aria-disabled");

        String expectedAttribute = "false";
        Assert.assertEquals("The field must not be disabled", expectedAttribute, actualAttribute);
    }

    @Test
    void testNameSelectorRecognizedCorrect (){
        WebElement passField = driver.findElement(By.name("passwd"));
        String actualAttribute = passField.getAttribute("placeholder");

        String expectedAttribute = "Enter your password";
        Assert.assertEquals("The placeholder must be \"Enter your password\"", expectedAttribute, actualAttribute);
    }

    @Test
    void testLinkTextRecognizedCorrect (){
        driver.findElement(By.linkText("Yandex")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("https://yandex.com/"));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Yandex";
        Assert.assertEquals("The title must be \"Yandex\"", expectedTitle, actualTitle);
    }

    @Test
    void testXpathSelectorRecognizedCorrect (){
        WebElement passwordField = driver.findElement(By.id(LOCATOR_PASSWORD_FIELD));
        passwordField.sendKeys(PASSWORD);
        WebElement loginButton = driver.findElement(By.id(LOCATOR_LOGIN_BUTTON_ON_PASSWORD_PAGE));
        loginButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("https://mail.yandex.com/"));

        WebElement subscriptionsButton = driver.findElement(By.xpath("//a[@href='#tabs/news']"));
        subscriptionsButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("Subscriptions"));
        String actualTitle = driver.getTitle();
        String expectedTitle = "Subscriptions — Yandex Mail";
        Assert.assertEquals("The title must be \"Subscriptions — Yandex Mail\"", expectedTitle, actualTitle);
    }

    @AfterEach
    void cleanup () {
        driver.quit();
    }
}

