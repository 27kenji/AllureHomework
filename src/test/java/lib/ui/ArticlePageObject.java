package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
    TITLE,
    TITLE_REPLACE,
    FOOTER_ELEMENT,
    SAVE_BUTTON,
    ADD_TO_LIST_BUTTON,
    MY_LIST_NAME_INPUT,
    MY_LIST_OK_BUTTON,
    CLOSE_ARTICLE_BUTTON,
    ADD_TO_SAVED_LIST_BUTTON,
    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON;

    protected lib.Platform platform;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchTitle(String substring) {
        return TITLE_REPLACE.replace("{SUBSTRING}", substring);
    }

    private static String getResultSavedArticleTitle(String name_of_folder) {
        return ADD_TO_SAVED_LIST_BUTTON.replace("{SUBSTRING}", name_of_folder);

    }


    /* TEMPLATES METHODS */

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on the page", 15);

    }

    @Step("Wait for title element and replace")
    public WebElement waitForTitleElementAndReplace(String substring) {
        String search_result_title = getResultSearchTitle(substring);
        return this.waitForElementPresent(search_result_title, "Cannot find title with substring " + substring, 10);

    }

    @Step("Get article title")
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if(Platform.getInstance().isMw()) {
            return title_element.getText();
        }
        return title_element.getAttribute("text");
    }

    @Step("Get article title and replace")
    public String getArticleTitleAndReplace(String substring) {
        WebElement title_element = waitForTitleElementAndReplace(substring);
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if(Platform.getInstance().isMw()) {
            return title_element.getText();
        }
        return title_element.getAttribute("text");
    }


    @Step("Swiping to footer on the article page")
    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
        this.testSwipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 10);
        } else if (Platform.getInstance().isMw()) {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }

    }

    @Step("Swipe up article")
    public void swipeUp() {
        this.testSwipeUpArticle(2000);
    }

    @Step("Adding the article to my list")
    public void addArticleToMyList(String name_of_folder) {
        if(Platform.getInstance().isMw()) {
            this.addArticleToMySaved();
        } else {
            this.waitForElementAndClick(SAVE_BUTTON, "Cannot find button to save article", 5);

            this.waitForElementAndClick(ADD_TO_LIST_BUTTON, "Cannot find button to save article", 5);
            this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder,
                    "Cannot put text into articles folder input", 5);
            this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot press OK button", 5);
        }

    }

    @Step("Closing article")
    public void closeArticle() {
        if(Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                    "Cannot close article", 5);
        } else {
            System.out.println("Method closeArticle does nothing for platform");
        }

    }

    @Step("Adding the second article to my list")
    public void secondAddArticleToMyList(String name_of_folder) {
        if(Platform.getInstance().isMw()) {
            this.addArticleToMySaved();
            this.waitForElementPresent(SAVE_BUTTON, "fdfd");
        } else {
            this.waitForElementAndClick(SAVE_BUTTON, "Cannot find button to save article", 5);
            this.waitForElementAndClick(ADD_TO_LIST_BUTTON,
                    "Cannot find 'Add to list' button", 5);
            String element = getResultSavedArticleTitle(name_of_folder);
            this.waitForElementAndClick(element, "Cannot find saved list", 15);
        }

    }

    @Step("Adding the second article to my list")
    public void articleTitleIsDisplayed(String title) {
        String element = getResultSearchTitle(title);
        this.assertElementPresent(element);
    }

    @Step("Remove article from saved")
    public void removeArticleFromSavedIfItAdded() {
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot click button to remove from add list", 15);
        } else {
            this.waitForElementPresent(SAVE_BUTTON, "Cannot find button to add to saved list after delete", 15);
        }

    }

    @Step("Adding the article to my list for mv")
    public void addArticleToMySaved() {
        if(Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }
            this.waitForElementAndClick(SAVE_BUTTON, "Cannot find options to add article", 15);
    }


}
