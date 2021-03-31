package com.company.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CurrentBoardHelper extends PageBase {
    String boardName;

    public CurrentBoardHelper(WebDriver driver, String boardName) {
        super(driver);
        this.boardName = boardName;
        PageFactory.initElements(driver, this);
    }

    public void openCurrentBoardPage(){
        WebElement qaHaifa8Board = driver.findElement(By
                .xpath("//a[@class = 'board-tile'][.//@title='" + boardName + "']"));
        qaHaifa8Board.click();
    }

//    public void waitUntilPageIsLoaded() {
//        waitUntilElementIsClickable(By.cssSelector(".mod-show-menu"), 10);
//    }

    public int getListsQuantity(){
        return driver.findElements(By.cssSelector(".list-header")).size();
    }

//    public void addNewList(String nameList) {
//        WebElement addListButton = driver.findElement(By.xpath("//span[@class='placeholder']/.."));
//        addListButton.click();
//        waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
//        WebElement titleListField = driver.findElement(By.xpath("//input[@name = 'name']"));
//        fillField(titleListField,nameList);
//        WebElement submitButton = driver.findElement(By.cssSelector(".js-save-edit"));
//        submitButton.click();
//        WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
//        cancelEditList.click();
//        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
//    }
//
//    public int getCardsQuantity(){
//        return driver.findElements(By.cssSelector(".js-card-details")).size();
//    }
//
//    public String getAddListButtonName(){
//        return driver.findElement(By.xpath("//span[@class='placeholder']")).getText();
//    }

    public void waitUntilBoardsButtonIsClickable() {
        waitUntilElementIsClickable(By.xpath
                ("//span[@class='placeholder']/.."), 7);
    }

    public void addAnotherListButtonClick() {
        WebElement addAnotherListButton = driver.findElement(By.xpath
                ("//span[@class='placeholder']/.."));
        addAnotherListButton.click();
    }

    public void enterListTitleField(String title) {
        waitUntilElementIsClickable(By.xpath
                ("//input[@name='name']"), 7);
        WebElement enterListTitleField = driver.findElement(By.xpath
                ("//input[@name='name']"));
        fillField(enterListTitleField, title);
    }

    public void saveNewListAddListButtonClick() {
        waitUntilElementIsClickable(By.cssSelector
                (".js-save-edit"), 7);
        WebElement addList = driver.findElement(By.cssSelector(".js-save-edit"));
        addList.click();
    }

//    public void findLastList() {
//        waitUntilAllElementsArePresent(By.cssSelector(".list-header"), 15);
//        int findLastList = driver.findElements(By.cssSelector(".open-card-composer")).size() - 1;
//        WebElement addCard = driver.findElements(By.cssSelector(".open-card-composer")).get(findLastList);
//        waitUntilElementIsClickable(By.xpath(".open-card-composer"), 7);
//        addCard.click();
//    }
//
//    public void enterNewCardToSelectedList(String title) {
//        waitUntilElementIsClickable(By.xpath("//textarea[@placeholder='Enter a title for this card…']"), 7);
//        WebElement addCardMenu = driver.findElement(By.xpath
//                ("//textarea[@placeholder='Enter a title for this card…']"));
//        addCardMenu.click();
//            waitUntilElementIsClickable(By.xpath
//                    ("//input[@name='name']"), 7);
//            WebElement enterListTitleField = driver.findElement(By.xpath
//                    ("//input[@name='name']"));
//            fillField(enterListTitleField, title);
//    }

    public void ifHaveNoListsAddNew(String nameList) {
        waitUntilElementIsClickable(By.xpath("//span[@class='placeholder']"), 7);
        WebElement addList = driver.findElement(By.xpath("//span[@class='placeholder']"));

        if(addList.getText().equals("Add a list")) {
            addList.click();
            waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),10);
            WebElement newNameListTitle = driver.findElement(By.cssSelector("input[name='name']"));
            fillField(newNameListTitle, nameList);
            WebElement saveList = driver.findElement(By.cssSelector("input.js-save-edit"));
            saveList.click();
            waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),10);
            WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelEditList.click();
        }
    }

    public void findAndRenameList(String lastListName) {
        int quantity = getListsQuantity()-1;
        WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(quantity);

        lastHeader.click();
        waitUntilElementIsClickable(By.cssSelector(".js-list-name-input"),10);

        WebElement lastNameList = driver.findElements
                (By.cssSelector(".js-list-name-input")).get(quantity);
        lastNameList.sendKeys(lastListName);
        lastNameList.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
        waitUntilAllElementsArePresent
                (By.cssSelector(".list-header"),15);
    }

    public String getLastListName() {
        WebElement lastNameList = driver.findElements
                (By.cssSelector(".js-list-name-input")).get(getListsQuantity()-1);
        if (getListsQuantity() == 0) return "Have no lists";
        return lastNameList.getText();
    }

    public void addCardToLastList() {
        int quantity = getListsQuantity()-1;
        waitUntilElementIsClickable(By.cssSelector(".open-card-composer"),10);

        WebElement addNewCard = driver.findElements
                (By.cssSelector(".open-card-composer")).get(quantity);
        addNewCard.click();

        waitUntilElementIsClickable(By.cssSelector(".js-card-title"),10);
        WebElement cardTitle = driver.findElement(By.cssSelector(".js-card-title"));
        fillField(cardTitle,"new card");

        //----- Define 'Add Card' button and click it -----------
        WebElement submitCard = driver.findElement(By.cssSelector(".js-add-card"));
        submitCard.click();

        //------ Click X-button -----------
        waitUntilElementIsClickable(By.cssSelector(".js-cancel"),10);
        driver.findElement(By.cssSelector(".js-cancel")).click();
    }

    public void findAndDeleteList() {
        waitUntilElementIsClickable(By.xpath("//div[@class='list-header-extras']"), 7);
        WebElement openListActionsMenu = driver.findElement(By.xpath("//div[@class='list-header-extras']"));
        openListActionsMenu.click();

        //------- Wait 'Archive List' option disappears ---------
        waitUntilElementIsClickable(By.xpath("//a[@class='js-close-list']"), 7);
        WebElement dropMenuArchiveList = driver.findElement(By.xpath("//a[@class='js-close-list']"));
        dropMenuArchiveList.click();
    }
}
