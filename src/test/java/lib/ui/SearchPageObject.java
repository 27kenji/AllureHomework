package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class SearchPageObject extends MainPageObject{
    protected static String
    SEARCH_INIT_ELEMENT,
    SEARCH_INPUT,
    SEARCH_CANCEL_BUTTON,
    SEARCH_RESULT_BY_SUBSTRING_TPL,
    SEARCH_RESULT_ELEMENT,
    SEARCH_EMPTY_RESULT_ELEMENT,
    SEARCH_ARTICLE_BY_TWO_LOCATORS;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUB}", substring);
    }

    public static String getResultSearchElementByTwoLocators(String title, String description) {
        String locator =  SEARCH_ARTICLE_BY_TWO_LOCATORS.replace("{SUBSTRING1}", title).replace("{SUBSTRING2}", description);
        return locator;
    }

    /* TEMPLATES METHODS */

    @Step("Initializing the search field")
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 10);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 10);
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    @Step("Waiting for search cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    @Step("Typing '{search_line}' to the search line")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    @Step("Waiting for search result")
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 10);
    }

    @Step("Waiting for search result and open it by substring")
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request ", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 10);

    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    @Step("Get count of articles")
    public int getCountOfArticles() {
        return this.countOfArticles(SEARCH_RESULT_ELEMENT, 15);
    }

    @Step("Waiting for element and description")
    public WebElement waitForElementByTitleAndDescription(String title, String description, long timeout) {
        String locator = getResultSearchElementByTwoLocators(title, description);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage("Cannot find element by two locators");
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Step("Get count of articles by title and subtitle")
    public int getCountOfArticlesByTitleAndSubtitle(String title, String description, long timeout) {
        String locator = getResultSearchElementByTwoLocators(title, description);
        return this.countOfArticles(locator, 15);

    }

}
