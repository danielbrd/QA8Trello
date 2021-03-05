package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginTest {
    WebDriver driver;
    @BeforeMethod
    public void initTests() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://trello.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }
    @Test
    public void applicationTest(){

    }

    @Test
    public void loginNegativeLoginIncorrect() throws InterruptedException {
        loginButtonMainScreen();
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField, "123");
        Thread.sleep(1500);
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField, "123");
        Thread.sleep(1500);

        loginButtonTrelloScreen();

        WebElement errorMessage = driver.findElement(By.id("error"));
        System.out.println("Error message: " + errorMessage.getText());

    }

    @Test
    public void loginNegative_incorrectPassword() throws InterruptedException {
        loginButtonMainScreen();

        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField, "dan.marley710@gmail.com");
        Thread.sleep(1500);

        loginButtonTrelloScreen();

        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField, "dan");
        Thread.sleep(1500);

        loginSubmitButton();

        WebElement errorMessage = driver.findElement(By.id("login-error"));
        System.out.println("Error message: " + errorMessage.getText());
    }

    @Test
    public void loginPositive() throws InterruptedException{
        loginButtonMainScreen();
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField, "dan.marley710@gmail.com");
        Thread.sleep(1500);

        loginButtonTrelloScreen();

        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField, "dan0524003966");
        Thread.sleep(1500);

        loginSubmitButton();

        WebElement boardButtonUpperLeftCorner = driver.findElement(By.xpath("//*[@class='MEu8ZECLGMLeab']"));
        System.out.println("Name of the button is: " + boardButtonUpperLeftCorner.getText());
    }



    public void loginButtonMainScreen() throws InterruptedException {
        WebElement loginIcon = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        Thread.sleep(5000);
    }
    public void loginButtonTrelloScreen() throws InterruptedException {
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Thread.sleep(1500);
    }
    public void loginSubmitButton() throws InterruptedException {
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();
        Thread.sleep(5000);
    }
    public void fillField(WebElement element, String value) {
        element.clear();
        element.click();
        element.sendKeys(value);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
