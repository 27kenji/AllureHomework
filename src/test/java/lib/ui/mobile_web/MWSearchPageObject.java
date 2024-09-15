package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:form>button[id='searchIcon']";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUB}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.mw-mf-page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.mw-search-nonefound";
        SEARCH_ARTICLE_BY_TWO_LOCATORS = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text, '{SUBSTRING1}')][contains(@text,'{SUBSTRING2}')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {super(driver);}
}
