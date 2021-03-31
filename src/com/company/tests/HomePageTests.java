package com.company.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
//----- Получение тайтла через метод getPageTitle() -----
    public void applicationTest(){
        Assert.assertEquals(homePage.getPageTitle(),
                "Trello", "Appl. is not Trello");
    }
}
