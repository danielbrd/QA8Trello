package com.company.tests;

import com.company.helpers.HomePageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    WebDriver driver;
    HomePageHelper homePage;

    @BeforeMethod
        public void startApplication() throws InterruptedException {
        driver = new ChromeDriver();
        homePage = new HomePageHelper(driver);
        driver.get("https://trello.com/");
        homePage.waitUntilPageIsLoaded();

    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until
                    (ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsArePresent(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until
                    (ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until
                    (ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public void fillField(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
    }
}
