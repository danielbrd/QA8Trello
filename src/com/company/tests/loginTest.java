package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.jar.JarOutputStream;

public class loginTest extends TestBase {

    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ----- Press login button -----
        WebElement loginIcon = driver.findElement(By.xpath
                ("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        waitUntilElementIsClickable(By.id("login"), 10);
    }

    @Test
    public void loginNegativeLoginIncorrect() throws InterruptedException {
        //----- Выявляем поля для Логина -----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField, "123");
        //----- Поле для Пароля -----
        waitUntilElementIsClickable(By.id("password"),7);
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField, "123");
        //----- Ожидание, пока кнопка Логин будет кликабельной -----
        waitUntilElementIsClickable(By.
                cssSelector("#error >.error-message"), 7);
        driver.findElement(By.id("login")).click();
        //----- Ожидание, пока появится окно ошибки -----
        waitUntilElementIsVisible(By.
                cssSelector("#error >.error-message"), 7);

        // --------- Print error message ----------
        WebElement errorMessage = driver.findElement(By.cssSelector("#error >.error-message"));
        System.out.println("Error-message: " + errorMessage.getText());

        Assert.assertTrue(errorMessage.getText().contains("There isn't an account"),
                "Error message doesn't exist");
    }

    @Test
    public void loginPositive() throws InterruptedException {
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"dan.marley710@gmail.com");
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian"), 7);
        driver.findElement(By.id("login")).click();
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.id("password"), 7);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("dan0524003966");

        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.id("login-submit"), 7);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the Home page loading and print 'Boards' button -------
        waitUntilElementIsClickable(By.xpath("//button[@aria-label = 'Open Boards Menu'"), 7);

        WebElement boardsButton = driver.findElement(By.xpath("//button[@aria-label = 'Open Boards Menu']"));
        Assert.assertTrue(boardsButton.isDisplayed(), "'Boards' button is not detected");
    }

    @Test
    public void negativePasswordIncorrect() throws InterruptedException {
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        waitUntilElementIsClickable(By.id("user"), 7);
        fillField(loginField,"dan.marley710@gmail.com");
        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(By.id("login"), 7);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        waitUntilElementIsClickable(By.id("password"), 7);
        driver.findElement(By.id("password")).sendKeys("incorrect");
        driver.findElement(By.id("login-submit")).click();

        //------Wait the error-message and print it -------
        waitUntilElementIsVisible(By.id("login-error"), 7);
        System.out.println("Error-message: " + driver
                .findElement(By.id("login-error")).getText());

        WebElement errorMessage = driver.findElement(By.id("login-error"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not detected");

    }
}
