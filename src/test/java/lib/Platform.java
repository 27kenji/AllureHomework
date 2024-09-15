package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/";

    private static Platform instance;
    private Platform() {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }
        else if(this.isIOS()) {
            return new IOSDriver(URL, this.getiOSDesiredCapabilities());
        } else if(this.isMw()) {
            return new ChromeDriver(this.getMwChromeOptions());
        }
        else {
            throw new Exception("Cannot detect type of the driver");
            }
        }


    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMw() {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "C:\\Users\\user\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\wikipedia_android.apk");
        return capabilities;
    }

    private DesiredCapabilities getiOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone SE");
        capabilities.setCapability("appium:platformVersion", "11.3");
        capabilities.setCapability("appium:app", "C:\\Users\\user\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\wikipedia_android.app");
        return capabilities;
    }

    private ChromeOptions getMwChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 8.0.0;" + "Pixel 2 XL Build/OPD1.170816.004) AppleWebKit/537.36 (KHTML, like Gecko) " + "Chrome/67.0.3396.99 Mobile Safari/537.36");

        ChromeOptions ChromeOptions = new ChromeOptions();
        ChromeOptions.addArguments("window-size=340,640");
        return ChromeOptions;
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);

    }

    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
