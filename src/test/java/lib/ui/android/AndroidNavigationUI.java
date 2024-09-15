package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
        CLOSE_SEARCH_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SAVED_ON_MENU = "xpath://android.widget.FrameLayout[@content-desc='Saved']";

    }

    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
