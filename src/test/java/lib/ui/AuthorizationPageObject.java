package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
    LOGIN_BUTTON = "xpath://a[@type='button']/*[contains(text(), 'Log in')]",
    LOGIN_INPUT = "css:input[name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BUTTON = "css:button[id='wpLoginAttempt']";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find LOGIN_BUTTON", 20);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click LOGIN_BUTTON", 20);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot put login", 15);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot put password", 15);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot click submit button", 15);
    }


}
