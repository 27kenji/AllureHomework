
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.OnboardingPageObject;

import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Test for checking count of articles in search results")
public class Ex3 extends CoreTestCase {

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test count of articles")
    @Description("We check count of articles")
    @Step("Starting test testCountOfArticles")
    public void testCountOfArticles() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        int elements = SearchPageObject.getCountOfArticles();
        assertTrue("Cannot find several articles", elements > 1);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

}




