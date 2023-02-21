package ui.pages;

import com.codeborne.selenide.SelenideElement;
import commonutils.uiUtils.BrowserInteractions;
import org.openqa.selenium.By;

import java.util.*;

public class RestaurantResultPage extends BrowserInteractions {
    private final static By minimumRadioButton = By.cssSelector("label[for='radio_1']");
    public static By restaurantHeaderTitle = By.cssSelector("h1[data-qa='restaurant-list-header']");
    public By sideBarResultCounterTitle = By.cssSelector("h3[data-qa=sidebar-result-counter]");
    private final static By minChargeTextForEachOpenRestaurant_Text = By.cssSelector("div[data-qa='mov-indicator-content'] span[data-qa='text']");
    private final By italianButton_cssSelector = By.cssSelector("div[data-qa='swipe-navigation-item-element'] div div");

    public RestaurantResultPage(){
        super();
    }

    public void clickMinimumRadioButton() {
        getSelenideElementByLocator(minimumRadioButton).scrollTo().click();
    }

    private static String getTextForMinimumBtn() {
        return getSelenideElementByLocator(minimumRadioButton).getText();
    }

    public static String getRestaurantNumberFromRadioText() {
        String[] getTextFromMin = getTextForMinimumBtn().split(" ");
        return Arrays.stream(getTextFromMin).filter(e -> e.contains("(")).findFirst().get().replaceAll("[\\(\\)]", "");
    }

    public static String getTheRestaurantListHeader() {
        if (getSelenideElementByLocator(restaurantHeaderTitle).isDisplayed()) {
            return getSelenideElementByLocator(restaurantHeaderTitle).getText();
        } else return "No opened restaurants";
    }

    public String getSideBarTitle() {
        return getSelenideElementByLocator(sideBarResultCounterTitle).scrollTo().getText();
    }

    public static List<String> getMinTextForALLRestaurants() throws NoSuchElementException {
        List<SelenideElement> elements = getALlElementsFromThePage(minChargeTextForEachOpenRestaurant_Text);
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> el = new ArrayList<>();
        elements.stream().map(element -> element.getText().split(",")[0]).forEach(el::add);
        return el;
    }

    public SelenideElement getItalianButtonFromMenu() {
        return getElementsByLocatorReturnList(italianButton_cssSelector).stream()
                .filter(e -> e.text().equals("Italian"))
                .findFirst().get();
    }
}
