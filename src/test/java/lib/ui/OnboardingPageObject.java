package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OnboardingPageObject extends MainPageObject{
    private static final String
    SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";

    public OnboardingPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void skipOnboarding() {
        this.skipOnboarding(SKIP_BUTTON, "Cannot click on 'Skip' button", 5);

    }
}
