package com.company.tests;

import com.company.helpers.BoardsPageHelper;
import com.company.helpers.LoginPageHelper;
import com.company.helpers.MemberMenuHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MemberMenuTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    MemberMenuHelper memberMenu;

    @BeforeMethod
    public void initTests(){
//        loginPage = new LoginPageHelper(driver);
//        boardsPage = new BoardsPageHelper(driver);
//        memberMenu = new MemberMenuHelper(driver);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        memberMenu = PageFactory.initElements(driver, MemberMenuHelper.class);

        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterLoginPasswordAttl(LOGIN, PASSWORD);
        boardsPage.waitUntillOpenBoardsButtonIsClickable();
    }

    @Test
    public void memberMenuFindEmail(){
        boardsPage.memberMenuClick();
        memberMenu.waitUntillMemberEmailIsVisible();
        memberMenu.getMemberEmail();
        System.out.println(memberMenu.getMemberEmail());
        Assert.assertTrue(memberMenu.getMemberEmail().contains("@"));
    }


}
