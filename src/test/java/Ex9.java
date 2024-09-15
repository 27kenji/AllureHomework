import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Epic("Tests for comparing article title")

public class Ex9 extends CoreTestCase {
    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test compare article title")
    @Description("We compare article title")
    @Step("Starting test testCompareArticleTitle")
    public void testCompareArticleTitle() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "language", 15);
        int count_of_articles = SearchPageObject.getCountOfArticlesByTitleAndSubtitle("Java", "language", 15);
        assertTrue("There are less than 3 articles in search result",count_of_articles >= 3);

    }

}