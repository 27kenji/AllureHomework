package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUi extends NavigationUI {
    static {
        MY_LISTS_LINK = "css:a[data-event-name='menu.watchlist']";

        /*"xpath://span[@class='toggle-list-item__label']";
        "css:a[data-event-name='menu.watchlist']"*/

        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";

    }

    public MWNavigationUi(RemoteWebDriver driver) {
        super(driver);
    }
}
