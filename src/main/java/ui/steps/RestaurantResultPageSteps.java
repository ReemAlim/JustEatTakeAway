package ui.steps;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.SoftAssertionError;
import org.opentest4j.MultipleFailuresError;
import ui.pages.RestaurantResultPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RestaurantResultPageSteps extends RestaurantResultPage {
    private static final SoftAssertions softAssertions = new SoftAssertions();

    public RestaurantResultPageSteps(){
        super();
    }

    public void verifyThatTheListHasLessThan10() {
        try {
            List<String> elements = getMinTextForALLRestaurants();
            if (!elements.isEmpty()) {
                softAssertions.assertThat(elements).allSatisfy(element -> assertThat(Integer.parseInt(element)).isLessThanOrEqualTo(10));
            }
            softAssertions.assertThat(getSideBarTitle()).contains(getRestaurantNumberFromRadioText());
            softAssertions.assertThat(driver.getCurrentUrl()).containsIgnoringCase("1000");
            softAssertions.assertAll();
        } catch (MultipleFailuresError e) {
            List<Throwable> failures = e.getFailures();
            throw new SoftAssertionError(failures.stream().map(Throwable::getMessage).collect(Collectors.toList()));
        }
    }

    public void verifyThatTheItalianRestaurantDisplayed() {
        try {
            String italianText = getTheRestaurantListHeader();
            if (!italianText.isEmpty()) {
                softAssertions.assertThat(italianText).contains("Italian");
            }
            softAssertions.assertThat(driver.getCurrentUrl()).containsIgnoringCase("Italian");
            softAssertions.assertAll();
        } catch (MultipleFailuresError e) {
            List<Throwable> failures = e.getFailures();
            throw new SoftAssertionError(failures.stream().map(Throwable::getMessage).collect(Collectors.toList()));
        }
    }
}
