package uiTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import ui.steps.HomePageSteps;
import ui.steps.RestaurantResultPageSteps;

import java.time.Duration;

public class JustEatMainTests extends HomePageSteps{
    RestaurantResultPageSteps restaurantResultPageSteps = new RestaurantResultPageSteps();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    public JustEatMainTests(){
        super();
    }

    @Test
    public void checkRestaurantsMinLessThan10(){
        searchForAnAddress();
        wait.until(ExpectedConditions.visibilityOfElementLocated(restaurantResultPageSteps.sideBarResultCounterTitle));
        wait.until(ExpectedConditions.urlContains("delivery"));
        restaurantResultPageSteps.clickMinimumRadioButton();
        restaurantResultPageSteps.verifyThatTheListHasLessThan10();
    }

    @Test
    public void checkItalianRestaurants(){
        searchForAnAddress();
        wait.until(ExpectedConditions.visibilityOfElementLocated(restaurantResultPageSteps.sideBarResultCounterTitle));
        restaurantResultPageSteps.getItalianButtonFromMenu().click();
        restaurantResultPageSteps.verifyThatTheItalianRestaurantDisplayed();
    }

}
