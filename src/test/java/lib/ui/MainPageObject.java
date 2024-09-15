package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.apache.tika.io.FilenameUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;
    protected Platform platform;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void skipOnboarding(String locator, String error_message, long timeoutInSeconds) {
        if(Platform.getInstance().isAndroid()) {
        WebElement elementSkip = waitForElementPresent(locator, error_message, timeoutInSeconds);
            elementSkip.click();
        } else System.out.println("Method testSwipeUpArticle does nothing for platform mobile_web");
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeout) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String errorMessage) {
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


    public void testSwipeUpArticle(long timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = (int) (size.width * 0.5);
            int start_y = (int) (size.height * 0.6);
            int end_y = (int) (size.height * 0.2);
            action.press(PointOption.point(x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe))).release().perform();

        } else {
            System.out.println("Method testSwipeUpArticle does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }


    public void swipeUpQuick() {
        if (driver instanceof AppiumDriver) {
            testSwipeUpArticle(200);
        } else {
            System.out.println("Method swipeUpQuick does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void scrollWebPageUp() {
        if(platform.isMw()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform");
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message);
        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 15).getLocation().getY();
        if(platform.isMw()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;

    }

    public void testSwipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        if (driver instanceof AppiumDriver) {
            int alreadySwiped = 0;
            while (driver.findElements(by).isEmpty()) {
                if (alreadySwiped > maxSwipes) {
                    waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                    return;
                }
                swipeUpQuick();
                ++alreadySwiped;
            }
        } else {
            System.out.println("Method testSwipeUpToFindElement does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    public void swipeElementToLeft(String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, error_message, 10);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action
                    .press(PointOption.point(left_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                    .moveTo(PointOption.point(right_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeElementToLeft does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeOnboarding(long timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            AndroidTouchAction action = new AndroidTouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int y = (int) (size.height * 0.5);
            int start_x = (int) (size.width * 0.2);
            int end_x = (int) (size.width * 0.6);
            action.press(PointOption.point(start_x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(end_x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe))).release().perform();
        } else {
            System.out.println("Method swipeOnboarding does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();

    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;

    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts) {
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts) {
            try {
                this.waitForElementAndClick(locator, error_message, 20);
                need_more_attempts = false;
            } catch (Exception e) {
                if(current_attempts > amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_message, 10);
                }
            }
            ++current_attempts;
        }
    }

    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeout) {
        WebElement element = waitForElementPresent(locator, error_message, timeout);
        return element.getAttribute(attribute);
    }

    public int countOfArticles(String locator, long timeout) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        int elements = driver.findElements(by).size();
        return elements;
    }

    public void assertElementPresent(String locator) {
        By by = this.getLocatorByString(locator);
        WebElement element = driver.findElement(by);
        Boolean title = element.isDisplayed();
        if (title == false) {
            String default_message = "A title is not present";
            throw new AssertionError(default_message);
        }
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        }

        else if(by_type.equals("id")) {
            return By.id(locator);
        }

        else if(by_type.equals("css")) {
            return By.cssSelector(locator);
        }

        else {
            throw new IllegalArgumentException("Cannot get type of locator");
        }
    }

    public String takeScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        } catch (Exception e) {
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }

}
