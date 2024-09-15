package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://*[@text='Java (programming language)']";
        TITLE_REPLACE = "xpath://*[@text='{SUBSTRING}']";
        FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
        SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        ADD_TO_LIST_BUTTON = "xpath://android.widget.FrameLayout//*[@resource-id='org.wikipedia:id/snackbar_action']";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        ADD_TO_SAVED_LIST_BUTTON = "xpath://*[@text='{SUBSTRING}']";
    }

    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);

    }
}
