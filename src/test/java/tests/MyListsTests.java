package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase2;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for my lists")
public class MyListsTests extends CoreTestCase2 {
    private static final String name_of_folder = "Learning programming";
    private static final String login = "27kenji", password = "2010_ats";

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value="Article"), @Feature(value="My lists")})
    @DisplayName("Save first article to my list")
    @Description("We open article and save it to my list")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value=SeverityLevel.BLOCKER)
    public void testSaveFirstArticleToMyList() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        OnboardingPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElementAndReplace("Java (programming language)");

        String article_title = ArticlePageObject.getArticleTitleAndReplace("Java (programming language)");

        ArticlePageObject.addArticleToMyList(name_of_folder);

        if(Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page", article_title, ArticlePageObject.getArticleTitle());
        }
        ArticlePageObject.closeArticle();
        NavigationUI.openNavigation();
        NavigationUI.closeSearchResults();
        NavigationUI.clickSavedOnMenu();
        NavigationUI.clickMyLists();
        NavigationUI.clickSavedOnMenu();
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }


}
