package com.company.tests;

import com.company.helpers.HomePageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    WebDriver driver;
    HomePageHelper homePage;
    public static final String LOGIN = "dan.marley710@gmail.com";
    public static final String PASSWORD = "dan0524003966";

    @BeforeMethod
        public void startApplication() throws InterruptedException {
        driver = new ChromeDriver();
//      homePage = new HomePageHelper(driver); ------------------------------ = Обычная инициализация
        homePage = PageFactory.initElements(driver, HomePageHelper.class); // = Способ для PageFactory
        //----Driver initialization. Open Trello application-------
        driver.get("https://trello.com/");
        homePage.waitUntilPageIsLoaded();
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

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until
                    (ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsPresent(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementDisappears(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOfElementLocated(locator));
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

}
