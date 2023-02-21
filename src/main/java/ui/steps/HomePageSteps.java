package ui.steps;

import com.codeborne.selenide.WebDriverRunner;
import ui.pages.HomePage;

public class HomePageSteps extends HomePage {

    public HomePageSteps(){
        super();
    }
    protected static void searchForAnAddress() {
        if (WebDriverRunner.isChrome()) {
            clearCookiesForChrome();
        }
        enterTextInSearchArea();
        clearCookiesAndSiteData();
        clickAddressFromDropdown();
    }

}
