package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);

    }
}
