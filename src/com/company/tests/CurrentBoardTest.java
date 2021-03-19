package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class CurrentBoardTest extends TestBase {

    @BeforeMethod
    public void initTests(){
        // ----- Press login button -----
        WebElement loginIcon = driver.findElement(By.xpath
                ("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        waitUntilElementIsClickable(By.id("user"), 7);

        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField, "dan.marley710@gmail.com");
        waitUntilElementIsClickable
                (By.xpath("//input[@value = 'Log in with Atlassian']"),10);
        driver.findElement(By.id("login")).click();

        //----- Fill in password field and press login-submit button-----------
        waitUntilElementIsClickable(By.id("password"), 7);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("dan0524003966");

        waitUntilElementIsClickable(By.id("login-submit"), 7);
        driver.findElement(By.id("login-submit")).click();

//        //------Wait the Home page loading and print 'Boards' button -------
//        waitUntilElementIsClickable(By.xpath("//button[@aria-label = 'Open Boards Menu']"),10);
//        System.out.println("Name of the button 'Boards': " + driver
//                .findElement(By.xpath("//button[@aria-label = 'Open Boards Menu']")).getText());

        //===== Open QA-8 Haifa board =====
        waitUntilElementIsClickable(By.xpath
                ("//a[@class='board-tile'][.//@title='QA8 Haifa']"), 7);
        WebElement qa8haifaBoard = driver.findElement(By.xpath
                ("//a[@class='board-tile'][.//@title='QA8 Haifa']"));
        qa8haifaBoard.click();
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);

    }

    @Test
    public void createNewList() throws InterruptedException {
        waitUntilElementIsClickable(By.xpath
                ("//span[@class='placeholder']/.."), 7);

        int firstListCount = driver.findElements(By.cssSelector(".list-header")).size()+1;

        WebElement addAnotherListButton = driver.findElement(By.xpath
                ("//span[@class='placeholder']/.."));
        addAnotherListButton.click();

        waitUntilElementIsClickable(By.xpath
                ("//input[@name='name']"), 7);
        WebElement enterListTitleField = driver.findElement(By.xpath
                ("//input[@name='name']"));
        fillField(enterListTitleField, "Test");

        waitUntilElementIsClickable(By.cssSelector
                (".js-save-edit"), 7);
        WebElement addListButton = driver.findElement(By.cssSelector(".js-save-edit"));
        addListButton.click();

        waitUntilElementIsClickable(By.cssSelector
                (".js-cancel-edit"), 7);
        WebElement cancelEditListIcon = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelEditListIcon.click();

        int lastListCount = driver.findElements(By.cssSelector(".list-header")).size();
        Assert.assertEquals(firstListCount, lastListCount);
    }

    @Test //SEL-07*
    public void changeListName() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

        //------- Change the header -----------------
        String newHeader = "newHeader";
        waitUntilElementIsClickable(By.cssSelector
                (".js-list-name-input"), 7);
        WebElement lastNameList = driver.findElements(By.cssSelector(".js-list-name-input")).get(lastList);
        lastNameList.sendKeys(newHeader);
        Thread.sleep(1500);
        lastNameList.sendKeys(Keys.ENTER);
        Thread.sleep(1500);
        driver.navigate().refresh();
        Thread.sleep(1500);

        lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);

        Assert.assertEquals(lastHeader.getText(), "newHeader");
    }

    @Test //SEL-08
    public void addCardToLastList(){
        WebElement addList = driver.findElement(By.xpath("//span[@class='placeholder']"));
        //----- If no list (name of the button is 'Add a list'), create the new list ----
        if(addList.getText().equals("Add a list")){
            addList.click();
            waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
            WebElement newNameList = driver.findElement(By.cssSelector("input[name='name']"));
            fillField(newNameList,"test");
            WebElement saveList = driver.findElement(By.cssSelector("input.js-save-edit"));
            saveList.click();
            waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),10);
            WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelEditList.click();
        }
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);

        //----- Находим последний по индексу элемент -----
        int findLastList = driver.findElements(By.cssSelector(".open-card-composer")).size() - 1;
        WebElement addCard = driver.findElements(By.cssSelector(".open-card-composer")).get(findLastList);
        waitUntilElementIsClickable(By.xpath(".open-card-composer"), 7);
        addCard.click();
//1
        WebElement addCardMenu = driver.findElement(By.xpath
                ("//textarea[@placeholder='Enter a title for this card…']"));
        waitUntilElementIsClickable(By.xpath("//textarea[@placeholder='Enter a title for this card…']"), 7);
        addCardMenu.click();
        fillField(addCardMenu,"Hello, teacher :)");

        WebElement buttonAddCard = driver.findElement(By.cssSelector(".js-add-card"));
        buttonAddCard.click();

        WebElement newCard = driver.findElement(By.cssSelector(".js-card-name"));
        Assert.assertEquals(newCard.getText(), "Hello, teacher :)");
    }

    @Test // SEL-09
    public void deleteLastList(){
        WebElement addList = driver.findElement(By.xpath("//span[@class='placeholder']"));
        //----- If no list (name of the button is 'Add a list'), create the new list ----
        if(addList.getText().equals("Add a list")){
            addList.click();
            waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
            WebElement newNameList = driver.findElement(By.cssSelector("input[name='name']"));
            fillField(newNameList,"test");
            WebElement saveList = driver.findElement(By.cssSelector("input.js-save-edit"));
            saveList.click();
            waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),10);
            WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelEditList.click();
        }
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
        //----- Находим последний по индексу элемент -----
        int findLastList = driver.findElements(By.xpath("//div[@class='list-header-extras']")).size() - 1;
        waitUntilElementIsClickable(By.xpath("//div[@class='list-header-extras']"), 7);
        WebElement listMenu = driver.findElements(By.xpath("//div[@class='list-header-extras']")).get(findLastList);
        listMenu.click();

        //------- Wait 'Archive List' option disappears ---------
        waitUntilElementIsClickable(By.xpath("//a[@class='js-close-list']"), 7);
        WebElement dropMenuArchiveList = driver.findElement(By.xpath("//a[@class='js-close-list']"));
        dropMenuArchiveList.click();

        // ----- Define the quantity of cards after  --------------
        int listCount = driver.findElements(By.cssSelector(".list-header")).size();
        Assert.assertEquals(listCount, findLastList);
    }
}


