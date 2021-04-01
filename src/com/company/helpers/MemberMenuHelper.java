package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MemberMenuHelper extends PageBase {
    @FindBy(xpath = "//span[contains(text(),'@')]")
    WebElement memberEmail;

    public MemberMenuHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntillMemberEmailIsVisible(){
        waitUntilElementIsVisible(memberEmail, 10);
    }

    public String getMemberEmail() {
        return memberEmail.getText();
    }
}

