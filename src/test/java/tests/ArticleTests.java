package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase2;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

@Epic("Tests for articles")

public class ArticleTests extends CoreTestCase2 {

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open article and make sure the title is expected")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value=SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

        String article_title = ArticlePageObject.getArticleTitle();
        /*ArticlePageObject.takeScreenshot("article_page");*/
        Assert.assertEquals("Unexpected title", "Appium", article_title);
    }

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Compare article title with expected one and replace")
    @Description("We open article and make sure the title is expected and replace it")
    @Step("Starting test testCompareTwoArticleTitleAndReplace")
    @Severity(value=SeverityLevel.MINOR)
    public void testCompareTwoArticleTitleAndReplace() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        String article_title = ArticlePageObject.getArticleTitleAndReplace("Appium");
        Assert.assertEquals("Unexpected title", "Appium", article_title);
    }

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and swipe it to the footer")
    @Step("Starting test testSwipeUpArticle")
    @Severity(value=SeverityLevel.MINOR)
    public void testSwipeUpArticle() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElementAndReplace("Appium");
        ArticlePageObject.swipeUp();
        ArticlePageObject.swipeUp();
    }

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Swipe article up to find element")
    @Description("We open an article and swipe it up to find element")
    @Step("Starting test testSwipeUpToFindElement")
    @Severity(value=SeverityLevel.NORMAL)
    public void testSwipeUpToFindElement() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElementAndReplace("Appium");
        ArticlePageObject.swipeToFooter();
    }

}
