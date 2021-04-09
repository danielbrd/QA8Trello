package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase{
    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    WebElement loginIcon;
    @FindBy(id = "user")
    WebElement loginField;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = "#error >.error-message")
    WebElement errorMessageNotAttl;
    @FindBy(xpath = "//input[@value = 'Log in with Atlassian']")
    WebElement loginAsAttlButton;

    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }

    public LoginPageHelper openLoginPage() {
//        WebElement loginIcon = driver.findElement
//                (By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        return this;
    }

//    public void waitUntilPageIsLoaded() {
        public LoginPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginField,10);
        waitUntilElementIsClickable(loginButton,20);
        return this;
    }

    public LoginPageHelper enterLoginPassNotAttl(String login, String password) {
        enterLoginNotAttl(login); //without 'this.'
        enterPasswordNotAttl(password);
        clickLoginInButtonNotAttl();
        return this;
    }

    public LoginPageHelper enterLoginPasswordAttl(String login, String password) {
        enterLoginNotAttl(login);
        clickLoginAttl();
        enterPasswordAttl(password);
        submitAttl();
        return this;
    }

    public LoginPageHelper enterLoginNotAttl(String value) {
//        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,value);
        return this;
    }

    public LoginPageHelper enterPasswordNotAttl(String value) {
        waitUntilElementIsClickable(passwordField,10);
//      WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,value);
// ----- To be sure that loginField and passwordField are already filled in -----
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public LoginPageHelper clickLoginInButtonNotAttl() {
//        waitUntilElementIsClickable(By.id("login"),20);
//        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(loginButton,20);
        loginButton.click();
        return this;
    }

    public String getErrorNotAttlMessage(){
        waitUntilElementIsVisible(errorMessageNotAttl,20);
        return errorMessageNotAttl.getText();
    }

    public LoginPageHelper clickLoginAttl() {
//        waitUntilElementIsClickable(By.xpath("//input[@value = 'Log in with Atlassian']"),10);
//        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(loginAsAttlButton,10);
        loginAsAttlButton.click();
        return this;
    }

    public LoginPageHelper enterPasswordAttl(String value) {
        waitUntilElementIsClickable(By.id("password"),10);
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,value);
        return this;
    }

    public LoginPageHelper submitAttl() {
        waitUntilElementIsClickable(By.id("login-submit"),10);
        driver.findElement(By.id("login-submit")).click();
        return this;
    }

    public String getErrorAttlMessage() {
        waitUntilElementIsVisible(By.id("login-error"),10);
        return driver.findElement(By.id("login-error")).getText();
    }
}
