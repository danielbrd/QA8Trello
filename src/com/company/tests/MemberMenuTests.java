package com.company.tests;

import com.company.helpers.BoardsPageHelper;
import com.company.helpers.LoginPageHelper;
import com.company.helpers.MemberMenuHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MemberMenuTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    MemberMenuHelper memberMenu;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        memberMenu = new MemberMenuHelper(driver);

        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterLoginPasswordAttl(LOGIN, PASSWORD);
        boardsPage.waitUntillOpenBoardsButtonIsClickable();
    }

    @Test
    public void memberMenuFindEmail(){
        boardsPage.memberMenuClick();
        memberMenu.getMembersEmail();
        System.out.println(memberMenu.getMembersEmail());
        Assert.assertTrue(memberMenu.getMembersEmail().contains("@"));
    }


}
