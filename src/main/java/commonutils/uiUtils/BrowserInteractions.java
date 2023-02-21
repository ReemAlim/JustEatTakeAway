package commonutils.uiUtils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class BrowserInteractions extends WebDriverManagerClass {
    public BrowserInteractions() {
        super();
    }

    public static SelenideElement getSelenideElementByLocator(By by) {
        return $(by);
    }

    public static ElementsCollection getElementsByLocator(By by) {
        return $$(by);
    }

    public static List<SelenideElement> getElementsByLocatorReturnList(By by) {
        return $$(by);
    }

    public static SelenideElement getElementById(String elementId) {
        return $(By.id(elementId));
    }

    public static List<SelenideElement> getALlElementsFromThePage(By locator) {
        List<SelenideElement> elements = $$(locator);
        return elements;
    }
}
