package com.company.tests;

import com.company.helpers.BoardsPageHelper;
import com.company.helpers.HomePageHelper;
import com.company.helpers.LoginPageHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    HomePageHelper homePage;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ------- Press login button  --------
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
    }

    @Test
    public void loginNegativeLoginIncorrect() {
        loginPage.enterLoginPassNotAttl("123", "psw");

        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
                .contains("There isn't an account"), "The error-message" +
                "doesn't contain 'There isn't an account'");
    }

    @Test
    public void loginNegativeLoginEmpty() {
        loginPage.enterLoginPassNotAttl("", "123");

        Assert.assertTrue(loginPage.getErrorNotAttlMessage()
                .contains("Missing"), "The error-message isn't correct");
    }

    @Test
    public void loginPositive() {
        loginPage.enterLoginPasswordAttl(LOGIN, PASSWORD);
        boardsPage.waitUntillPageIsLoaded();

        Assert.assertEquals("Boards", boardsPage.getNameBoardsButton());
    }
//
//    public void enterLoginPasswordAttl(String login, String password) {
//        loginPage.enterLoginNotAttl(LOGIN);
//        loginPage.clickLoginAttl();
////      WebElement loginField = driver.findElement(By.id("user"));
////      fillField(loginField, "dan.marley710@gmail.com");
////      //Thread.sleep(2000);
//        //Thread.sleep(2000);
//        loginPage.enterPasswordAttl(PASSWORD);
////      waitUntilElementIsClickable(By.id("password"), 10);
////       //----- Fill in password field and press login-submit button-----------
////      driver.findElement(By.id("password")).click();
////      driver.findElement(By.id("password")).sendKeys("dan0524003966");
//        //Thread.sleep(2000);
//        loginPage.submitAttl();
//    }

    @Test
    public void negativePasswordIncorrect() {
//        //---- Fill in login-field and press "login with Attlassian"----
//        WebElement loginField = driver.findElement(By.id("user"));
//        waitUntilElementIsClickable(By.id("user"), 7);
//        fillField(loginField, "dan.marley710@gmail.com");
//        driver.findElement(By.id("login")).click();
//        waitUntilElementIsClickable(By.id("login"), 7);
        loginPage.enterLoginPasswordAttl(LOGIN, "123");
//        //----- Fill in password field and press login-submit button-----------
//        driver.findElement(By.id("password")).click();
//        waitUntilElementIsClickable(By.id("password"), 7);
//        driver.findElement(By.id("password")).sendKeys("incorrect");
//        driver.findElement(By.id("login-submit")).click();
        loginPage.getErrorAttlMessage();

        Assert.assertTrue(loginPage.getErrorAttlMessage().contains("email"));
    }
}