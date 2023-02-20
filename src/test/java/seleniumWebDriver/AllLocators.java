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

public class AllLocators {
    private WebDriver driver;

    @BeforeEach
    void setup () {
        driver = new ChromeDriver();
        driver.get("https://mail.yandex.com");
        WebElement logInButton = driver.findElement(By.xpath("//a[@href='https://passport.yandex.com/auth?retpath=https%3A%2F%2Fmail.yandex.com']"));
        logInButton.click();

        driver.findElement(By.id("passp-field-login")).sendKeys("v1k.pa");
        driver.findElement(By.id("passp:sign-in")).click();
    }

    @Test
    void cssSelector (){
        WebElement passField = driver.findElement(By.cssSelector("#passp-field-passwd"));
        String actualAttribute = passField.getAttribute("placeholder");

        String expectedAttribute = "Enter your password";
        Assert.assertEquals(expectedAttribute, actualAttribute);

    }

    @Test
    void idSelector (){
        WebElement logInButton = driver.findElement(By.id("passp:sign-in"));
        String actualAttribute = logInButton.getAttribute("aria-disabled");

        String expectedAttribute = "false";
        Assert.assertEquals(expectedAttribute, actualAttribute);
    }

    @Test
    void nameSelector (){
        WebElement passField = driver.findElement(By.name("passwd"));
        String actualAttribute = passField.getAttribute("placeholder");

        String expectedAttribute = "Enter your password";
        Assert.assertEquals(expectedAttribute, actualAttribute);
    }

    @Test
    void linkText (){
        driver.findElement(By.linkText("Yandex")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("https://yandex.com/"));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Yandex";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void xpathSelector (){
        WebElement passwordField = driver.findElement(By.id("passp-field-passwd"));
        passwordField.sendKeys("v1k.papass");
        WebElement loginButton = driver.findElement(By.id("passp:sign-in"));
        loginButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("https://mail.yandex.com/"));

        WebElement subscriptionsButton = driver.findElement(By.xpath("//a[@href='#tabs/news']"));
        subscriptionsButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("Subscriptions"));
        String actualTitle = driver.getTitle();
        String expectedTitle = "Subscriptions — Yandex Mail";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @AfterEach
    void cleanup () {
        driver.quit();
    }
}

