package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MemberMenuHelper extends PageBase {
    public MemberMenuHelper(WebDriver driver) {
        super(driver);
    }

    public String getMemberEmail() {
        waitUntilElementIsPresent(By.xpath
                ("//section[@data-test-id='header-member-menu-popover']"), 10);
        WebElement memberMenu = driver.findElement(By.xpath
                ("//span[contains(text(),'@')]"));
        return memberMenu.getText();
    }
}

