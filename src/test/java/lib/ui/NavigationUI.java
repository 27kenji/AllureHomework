package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject {

    protected static String
    CLOSE_SEARCH_BUTTON,
    SAVED_ON_MENU,
    MY_LISTS_LINK,
    OPEN_NAVIGATION;

    protected lib.Platform platform;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void closeSearchResults() {
        if(Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(CLOSE_SEARCH_BUTTON,
                    "Cannot close search result", 5);
        } else {
            System.out.println("This method does nothing for platform");
        }
    }

    public void clickSavedOnMenu() {
        if(Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(SAVED_ON_MENU,
                    "Cannot find navigation button to 'Saved'", 5);
        } else {
            System.out.println("This method does nothing for platform");
        }
    }

    public void openNavigation() {
        if(Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click OPEN_NAVIGATION button", 15);
        } else {
            System.out.println("This method does nothing for platform");
        }
    }

    public void clickMyLists() {
        if(Platform.getInstance().isMw()) {
            /*this.waitForElementAndClick(MY_LISTS_LINK, "Cannot find navigation button to 'My lists'", 15);*/
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "Cannot find navigation button to 'My lists'", 5);
        } else {

            System.out.println("This method does nothing for platform");
        }
    }

    public void waitForElementMY_LISTS_LINK() {

        this.waitForElementPresent(MY_LISTS_LINK, "Cannot find navigation button to 'My lists'", 15);
    }

}
