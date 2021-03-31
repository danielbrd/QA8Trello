package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardsPageHelper extends PageBase {
        @FindBy(xpath = "//button[@aria-label = 'Open boards menu']")
        WebElement openBoardsButton;


    public BoardsPageHelper(WebDriver driver) {
        super(driver);
    }


    public void waitUntillOpenBoardsButtonIsClickable() {
        //------Wait the Home page loading and print 'Boards' button -------
        waitUntilElementIsClickable(openBoardsButton, 10);
    }

    public String getNameBoardsButton() {
        waitUntilElementIsClickable(openBoardsButton, 10);
        return openBoardsButton.getText();
    }

//    public void goToQA8HaifaBoard() {
//        waitUntilElementIsClickable(By.xpath
//                ("//a[@class='board-tile'][.//@title='QA8 Haifa']"), 7);
//        WebElement qa8haifaBoard = driver.findElement(By.xpath
//                ("//a[@class='board-tile'][.//@title='QA8 Haifa']"));
//        qa8haifaBoard.click();
//        waitUntilAllElementsArePresent(By.cssSelector(".list-header"), 15);
//    }

    public void memberMenuClick() {
        WebElement memberMenu = driver.findElement
                (By.cssSelector(".js-open-header-member-menu"));
        memberMenu.click();
    }
}
