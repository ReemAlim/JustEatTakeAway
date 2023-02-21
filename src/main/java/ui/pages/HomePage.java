package ui.pages;

import commonutils.uiUtils.BrowserInteractions;
import org.openqa.selenium.By;

public class HomePage extends BrowserInteractions {
    protected static By search_textBx = By.cssSelector("input[data-qa='location-panel-search-input-address-element']");
    protected static By address_list_li = By.cssSelector("li[data-qa='location-panel-results-item-element']");
    protected static By cookies_ok_button = By.cssSelector("div[data-qa='privacy-settings'] button[data-qa='privacy-settings-action-info']");
    static String address = "Watzmannstraße 4, 81541 München";

    public HomePage(){
        super();
    }

    protected static void enterTextInSearchArea() {
        getSelenideElementByLocator(search_textBx).setValue(address);
    }

    protected static void clickAddressFromDropdown() {
        getSelenideElementByLocator(address_list_li).click();
    }

    protected static void acceptCookies() {
        getSelenideElementByLocator(cookies_ok_button).click();
    }
}
