package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MemberMenuHelper extends PageBase {
    public MemberMenuHelper(WebDriver driver) {
        super(driver);
    }
    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id ='header-member-menu-profile']"),10);
    }


    public String getMembersEmail() {
//        waitUntilElementIsPresent(By.xpath
//                ("//section[@data-test-id='header-member-menu-popover']"), 10);
        WebElement memberMenu = driver.findElement(By.xpath
                ("//span[contains(text(),'@')]"));
        return memberMenu.getText();
    }
}

