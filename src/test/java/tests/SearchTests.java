package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.CoreTestCase2;
import lib.ui.MainPageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

@Epic("Tests for searching articles")
public class SearchTests extends CoreTestCase2 {

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test search results")
    @Description("We find article by title")
    @Step("Starting test testSearch")
    public void testSearch() {
       /*WebElement elementSkip1 = driver.findElementByXPath("//*[contains(@text, 'Continue')]");
       elementSkip1.click();
       WebElement elementSkip2 = driver.findElementByXPath("//*[contains(@text, 'Continue')]");
       elementSkip2.click();
       WebElement elementSkip3 = driver.findElementByXPath("//*[contains(@text, 'Continue')]");
       elementSkip3.click();
       WebElement elementSkip4 = driver.findElementById("org.wikipedia:id/fragment_onboarding_done_button");
       elementSkip4.click();
       /*WebElement elementTap = waitForElement("//*[contains(@text, 'Search Wikipedia')]", "Cannot find Search Wikipedia input");
       elementTap.click();
       WebElement elementEnter = driver.findElementByXPath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']");
       elementEnter.sendKeys("Java");*/

        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test cancel search")
    @Description("We cancel search result")
    @Step("Starting test testCancelSearch")
    public void testCancelSearch() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test clear search")
    @Description("We clear search result")
    @Step("Starting test testClearSearch")
    public void testClearSearch() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test amount of not empty search")
    @Description("We check amount of not empty search results")
    @Step("Starting test testAmountOfNotEmptySearch")
    public void testAmountOfNotEmptySearch() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        OnboardingPageObject.skipOnboarding();
        String search_line = "Григорий Перельман";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue("Found too few results", amount_of_search_results > 0);
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test amount of empty search")
    @Description("We check amount of empty search results")
    @Step("Starting test testAmountOfEmptySearch")
    public void testAmountOfEmptySearch() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        OnboardingPageObject.skipOnboarding();
        String search_line = "hgfhfdffh";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }
}
