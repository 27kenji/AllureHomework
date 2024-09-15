package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        TITLE_REPLACE = "xpath://span[contains(@class,'mw-page-title-main')][contains(text(), '{SUBSTRING}')]";
        /*"xpath://li[contains(@class,'page-summary with-watchstar')]//h3[contains(text(), '{SUBSTRING}')]";*/
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://a[@id='ca-watch']/*[contains(text(), 'Unwatch')]";
        SAVE_BUTTON = "xpath://span[contains(@class,'minerva-icon minerva-icon--star-base20')]";

                /*"xpath://a[@id='ca-watch']/*[contains(text(), 'Watch')]";*/

        ADD_TO_LIST_BUTTON = "xpath://span[contains(@class,'minerva-icon minerva-icon--star-base20')]";
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "//*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
        ADD_TO_SAVED_LIST_BUTTON = "//*[contains(@text,'{SUBSTRING}')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);

    }
}
