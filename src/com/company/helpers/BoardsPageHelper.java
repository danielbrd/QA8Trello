package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardsPageHelper extends PageBase {

    public BoardsPageHelper(WebDriver driver) {
        super(driver);
    }


    public void waitUntillOpenBoardsButtonIsClickable() {
        //------Wait the Home page loading and print 'Boards' button -------
        waitUntilElementIsClickable
                (By.xpath("//button[@aria-label = 'Open boards menu']"), 10);
//        System.out.println("Name of the button 'Boards': " + driver.findElement
//                (By.xpath("//button[@aria-label = 'Open boards menu']")).getText());
        ;
    }

    public String getNameBoardsButton() {
        waitUntilElementIsClickable(By
                .xpath("//button[@aria-label = 'Open boards menu']"), 5);
        WebElement boardsButton = driver.findElement(By
                .xpath("//button[@aria-label = 'Open boards menu']"));
        return boardsButton.getText();
    }

    public void goToQA8HaifaBoard() {
        waitUntilElementIsClickable(By.xpath
                ("//a[@class='board-tile'][.//@title='QA8 Haifa']"), 7);
        WebElement qa8haifaBoard = driver.findElement(By.xpath
                ("//a[@class='board-tile'][.//@title='QA8 Haifa']"));
        qa8haifaBoard.click();
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"), 15);
    }

    public void memberMenuClick() {
        WebElement memberMenu = driver.findElement
                (By.cssSelector(".js-open-header-member-menu"));
        memberMenu.click();
        waitUntilElementIsClickable(By.xpath
                ("//a[@data-test-id='header-member-menu-profile']"), 10);

    }
}
