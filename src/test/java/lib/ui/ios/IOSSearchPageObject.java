package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text, '{SUB}')]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
        SEARCH_ARTICLE_BY_TWO_LOCATORS = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text, '{SUBSTRING1}')][contains(@text,'{SUBSTRING2}')]";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
