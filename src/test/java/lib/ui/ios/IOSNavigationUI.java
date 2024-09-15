package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
        CLOSE_SEARCH_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
        SAVED_ON_MENU = "//android.widget.FrameLayout[@content-desc='Saved']";
    }

    public IOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
