package commonutils.uiUtils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;

public class WebDriverManagerClass {
    static ConfigReader config = new ConfigReader();
    public static WebDriver driver;

    @BeforeTest
    public static void setUpChrome() {
        open(config.getProperty("url"));
        driver = WebDriverRunner.getAndCheckWebDriver();
        maximizeWindow();
    }

    public WebDriverManagerClass() {
        setUpChrome();
//        setUpFireFox();
    }

    public static void clearCookiesAndSiteData() {
        driver.manage().deleteAllCookies();
        Selenide.executeJavaScript("window.localStorage.clear();");
        Selenide.executeJavaScript("window.sessionStorage.clear();");
    }

    public static void clearCookiesForChrome() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
        refresh();
    }

    private static void setUpFireFox() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("geo.enabled", false);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        driver = new FirefoxDriver(options);
        driver.get(config.getProperty("url"));
        WebDriverRunner.setWebDriver(driver);
    }

    private static void maximizeWindow() {
        driver.manage().window().maximize();
    }
}

