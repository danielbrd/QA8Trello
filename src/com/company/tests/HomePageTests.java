package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
//----- Получение тайтла через метод getPageTitle() -----
    public void applicationTest(){
        Assert.assertEquals(homePage.getPageTitle(),
                "Trello", "Appl. is not Trello");
    }
}
