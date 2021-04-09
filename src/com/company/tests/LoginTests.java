package com.company.tests;

import com.company.helpers.BoardsPageHelper;
import com.company.helpers.HomePageHelper;
import com.company.helpers.LoginPageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests() {
        // ------- Press login button  --------
//        loginPage = new LoginPageHelper(driver);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
//        boardsPage = new BoardsPageHelper(driver);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
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
        boardsPage.waitUntillOpenBoardsButtonIsClickable();

        Assert.assertEquals("Boards", boardsPage.getNameBoardsButton());
    }

    @Test
    public void negativePasswordIncorrect() {
//        //---- Fill in login-field and press "login with Attlassian"----
        loginPage.enterLoginPasswordAttl(LOGIN, "123")
//        //----- Fill in password field and press login-submit button-----------
                .getErrorAttlMessage();

        Assert.assertTrue(loginPage.getErrorAttlMessage().contains("email"));
    }
}