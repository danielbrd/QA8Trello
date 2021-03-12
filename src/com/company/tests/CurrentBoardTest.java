package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.WatchEvent;

public class CurrentBoardTest extends TestBase {

    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ----- Press login button -----
        WebElement loginIcon = driver.findElement(By.xpath
                ("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        waitUntilElementIsClickable(By.id("login"), 10);

        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField, "dan.marley710@gmail.com");
        Thread.sleep(1000);

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Thread.sleep(1000);

        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField, "dan0524003966");
        Thread.sleep(1000);

        WebElement loginSubmitButton = driver.findElement(By.id("login-submit"));
        loginSubmitButton.click();
        Thread.sleep(5000);

        //===== Open QA-8 Haifa board =====
        WebElement qa8haifaBoard = driver.findElement(By.xpath
                ("//a[@class='board-tile'][.//@title='QA8 Haifa']"));
        qa8haifaBoard.click();
        Thread.sleep(1000);

        WebElement boardButtonUpperLeftCorner =
                driver.findElement(By.xpath("//*[@class='MEu8ZECLGMLeab']"));
//        Assert.assertTrue(boardButtonUpperLeftCorner.isDisplayed(), "Boards");
    }

    @Test
    public void createNewList() throws InterruptedException {
        WebElement addAnotherListButton = driver.findElement(By.xpath
                ("//span[@class='placeholder']/.."));
        addAnotherListButton.click();
        Thread.sleep(1500);

        WebElement enterListTitleField = driver.findElement(By.xpath
                ("//input[@name='name']"));
        fillField(enterListTitleField, "Test");
        Thread.sleep(800);
        WebElement addListButton = driver.findElement(By.cssSelector(".js-save-edit"));
        addListButton.click();
        Thread.sleep(800);
        WebElement cancelEditListIcon = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelEditListIcon.click();
        Thread.sleep(1500);

        WebElement qa8cardsList = driver.findElement(By.id("board"));

        Assert.assertTrue(qa8cardsList.getText().contains("Test"),
                "Card doesn't exists");
    }

    @Test
    public void changeListName() throws InterruptedException {
        WebElement addList = driver.findElement(By.xpath("//span[@class='placeholder']"));
        //----- If no list (name of the button is 'Add a list'), create the new list ----
        if (addList.getText().equals("Add a list")) {
            addList.click();
            WebElement newNameList = driver.findElement(By.cssSelector("input[name='name']"));
            fillField(newNameList, "test");
            WebElement saveList = driver.findElement(By.cssSelector("input.js-save-edit"));
            saveList.click();
            Thread.sleep(2000);
            WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelEditList.click();
            Thread.sleep(2000);
        }
        // ----- Define the index of the last list --------------
        int lastList = driver.findElements(By.cssSelector(".list-header")).size() - 1;

        //-------- Click on the header--------------
        WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);

        lastHeader.click();
        Thread.sleep(2000);

        //------- Change the header -----------------
        String newHeader = "newHeader";
        WebElement lastNameList = driver.findElements(By.cssSelector(".js-list-name-input")).get(lastList);
        lastNameList.sendKeys(newHeader);
        Thread.sleep(2000);
        lastNameList.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);

        lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);

        Assert.assertEquals(lastHeader.getText(), "newHeader");
    }

    @Test
    public void deleteLastList(){
        //----- Находим последний по индексу элемент -----
        int findLastList = driver.findElements(By.xpath("//div[@class='list-header-extras']")).size() - 1;
        WebElement listMenu = driver.findElements(By.xpath("//div[@class='list-header-extras']")).get(findLastList);
        waitUntilElementIsClickable(By.xpath("//div[@class='list-header-extras']"), 7);
        listMenu.click();

        WebElement dropMenuArchiveList = driver.findElement(By.xpath("//a[@class='js-close-list']"));
        waitUntilElementIsClickable(By.xpath("//a[@class='js-close-list']"), 7);
        dropMenuArchiveList.click();
    }

    @Test
    public void addCardToLastList(){
        //----- Находим последний по индексу элемент -----
        int findLastList = driver.findElements(By.cssSelector(".open-card-composer")).size() - 1;
        WebElement addCard = driver.findElements(By.cssSelector(".open-card-composer")).get(findLastList);
        waitUntilElementIsClickable(By.xpath(".open-card-composer"), 7);
        addCard.click();

        WebElement addCardMenu = driver.findElement(By.xpath
                ("//textarea[@placeholder='Enter a title for this card…']"));
        waitUntilElementIsClickable(By.xpath("//textarea[@placeholder='Enter a title for this card…']"), 7);
        addCardMenu.click();
        fillField(addCardMenu,"Hello, teacher :)");

        WebElement button = driver.findElement(By.cssSelector(".js-add-card"));
        button.click();

        WebElement newCard = driver.findElement(By.cssSelector(".js-card-name"));
        Assert.assertEquals(newCard.getText(), "Hello, teacher :)");
    }
}


