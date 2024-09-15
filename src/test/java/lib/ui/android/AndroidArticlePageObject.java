package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://*[@text='Automation for Apps']";
                TITLE_REPLACE = "xpath://*[contains(@text,'{SUBSTRING}')]";
                FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
                SAVE_BUTTON = "id:org.wikipedia:id/page_save";
                ADD_TO_LIST_BUTTON = "xpath://android.widget.FrameLayout//*[@resource-id='org.wikipedia:id/snackbar_action']";
        /*"xpath://android.widget.FrameLayout//*[@resource-id='org.wikipedia:id/snackbar_action']"*/
                MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
                MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
                CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                ADD_TO_SAVED_LIST_BUTTON = "xpath://*[contains(@text,'{SUBSTRING}')]";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);

    }
}
