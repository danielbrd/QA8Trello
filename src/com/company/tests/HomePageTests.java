package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void applicationTest() {
        System.out.println("Title: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),
                "Trello", "Appl. is not Trello");
    }
}