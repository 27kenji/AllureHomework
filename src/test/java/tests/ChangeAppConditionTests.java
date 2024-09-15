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

@Epic("Tests for change app condition")

public class ChangeAppConditionTests extends CoreTestCase2 {

    @Test
    @DisplayName("Change screen orientation")
    @Description("We change screen orientation on search results")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value=SeverityLevel.NORMAL)
    public void testChangeScreenOrientationOnSearchResults() {
        if(Platform.getInstance().isMw()) {
            return;
        }
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        String title_before_rotation = ArticlePageObject.getArticleTitleAndReplace("Automation for Apps");
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitleAndReplace("Automation for Apps");
        Assert.assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitleAndReplace("Automation for Apps");
        Assert.assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);

    }

    @Test
    @DisplayName("Check search article in background")
    @Description("We check search article in background")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value=SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground() {
        if(Platform.getInstance().isMw()) {
            return;
        }
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForSearchResult("Automation for Apps");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Automation for Apps");
    }

}
