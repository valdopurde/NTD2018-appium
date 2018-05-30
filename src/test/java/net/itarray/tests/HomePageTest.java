package net.itarray.tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.utils.DriverUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by zad on 05/06/17.
 */
public class HomePageTest {

    private final static String packageName = "com.denyszaiats.myreactions";
    private AndroidDriver driver;
    private DriverUtils driverUtils;
    private final static int CLICKS = 100;
    private ResponsiveUIValidator uiValidator;
    private SoftAssertions softly;

    @Before
    public void setUp() throws MalformedURLException {
        Map<String, String> env = System.getenv();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", "Android");
        capabilities.setCapability("version", "6.0");
        // capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", env.get("APP"));
        capabilities.setCapability("deviceName", "emulator-5554"); // adb devices
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driverUtils = new DriverUtils(driver);
        uiValidator = new ResponsiveUIValidator(driver);
        softly = new SoftAssertions();
    }

    @Test
    public void testThatHomePageIsDisplayedCorrectly() {
        boolean isEnFlagAligned = uiValidator.snapshot("Lang En flag")
                .findElement(driver.findElement(By.id(packageName + ":id/imageLangEn")), "Eng flag")
                .isCenteredOnPageHorizontally() // Failed case
                .isTopAlignedWith(driver.findElement(By.id(packageName + ":id/imageLangEn")), "UA flag")
                .hasEqualSizeAs(driver.findElement(By.id(packageName + ":id/imageLangEn")), "UA flag")
                .validate();

        softly.assertThat(isEnFlagAligned).overridingErrorMessage("En flag is aligned properly");
        driver.findElement(By.id(packageName + ":id/imageLangEn")).click();
        driver.findElement(By.id(packageName + ":id/skip_login_button")).click();
        acceptModal();
        closeNavigationDrawer();
        openNavigationDrawer();
        openNavigationDrawer();
        openMenu("Help");
        softly.assertThat(driverUtils.findElementVertically(DriverUtils.Strategy.XPATH, "//android.view.View[@content-desc='Home page']", 10))
                .isNotNull()
                .overridingErrorMessage("Homepage not found");
        softly.assertThat(driverUtils.findElementVertically(DriverUtils.Strategy.XPATH, "//android.view.View[@content-desc='Colors memorising']", 10))
                .isNotNull();
        softly.assertThat(driverUtils.findElementVertically(DriverUtils.Strategy.XPATH, "//android.view.View[@content-desc='Crazy Fingers']", 10))
                .isNotNull();
        softly.assertThat(driverUtils.findElementVertically(DriverUtils.Strategy.XPATH, "//android.view.View[@content-desc='Colors in the Fog']", 10))
                .isNotNull();
        acceptModal();
        openMenu("Crazy Fingers");
        acceptModal();
        chooseHandAndFingersAndStartGame(Hand.RIGHT, Finger.INDEX);
        openNavigationDrawer();
        openNavigationDrawer();
        openMenu("Home");
        acceptModal();
        chooseHandAndFingerAndClickShowResults(Hand.RIGHT, Finger.INDEX);
        // uiValidator.generateReport("Report");
        softly.assertThat(driverUtils.findElement(DriverUtils.Strategy.XPATH, "//android.widget.TextView[contains(@text,'Max clicks per 10 seconds')]"))
                .isNotNull()
                .overridingErrorMessage("Max clicks not found");
    }

    @After
    public void tearDown() {
        softly.assertAll();

        if (driver != null) {
            driver.quit();
        }
    }

    private void openNavigationDrawer() {
        if (driver.findElements(By.id(packageName + ":id/list_slidermenu")).size() == 0) {
            driver.findElement(By.id("android:id/up")).click();
        }
    }

    private void closeNavigationDrawer() {
        if (driver.findElements(By.id(packageName + ":id/list_slidermenu")).size() > 0) {
            driver.findElement(By.id("android:id/up")).click();
        }
    }

    private void openMenu(String menuName) {
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + menuName + "']")).click();
    }

    private void acceptModal() {
        driverUtils.findElementVertically(DriverUtils.Strategy.ID, packageName + ":id/buttonGuideOk", 10).click();
    }

    public void chooseHandAndFingersAndStartGame(Hand hand, Finger finger) {
        driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/handButton").click();
        switch (hand) {
            case LEFT:
                driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/imageHandLeft").click();
                break;
            case RIGHT:
                driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/imageRightHand").click();
                break;
        }
        driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/fingerButton").click();
        switch (finger) {
            case THUMB:
                driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/imageThumbFinger").click();
                break;
            case INDEX:
                driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/imageIndexFinger").click();
                break;
            case MIDDLE:
                driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/imageMiddleFinger").click();
                break;
            case RING:
                driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/imageRingFinger").click();
                break;
            case PINKY:
                driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/imagePinkyFinger").click();
                break;
        }

        WebElement startButton = driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/startButton");

        boolean startButtonResult = uiValidator.snapshot("Checking Play Start button")
                .findElement(startButton, "Start button")
                .isCenteredOnPageHorizontally()
                .isCenteredOnPageVertically()
                .validate();

        softly.assertThat(startButtonResult).overridingErrorMessage("Start Button is aligned properly");
        driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/startButton").click();
        WebElement tapButton = driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/imageTapButton");

        boolean tapButtonResult = uiValidator.snapshot("Tap button")
                .findElement(tapButton, "Tap button")
                .isCenteredOnPageHorizontally()
                .isCenteredOnPageVertically() // Will be failed
                .hasWidth(Condition.between(300).and(400)) // Will be failed
                .validate();

        softly.assertThat(startButtonResult).overridingErrorMessage("Start Button is aligned properly");
        softly.assertThat(tapButtonResult).overridingErrorMessage("Tap Button is aligned properly");
        Point location =  tapButton.getLocation();
        Dimension size =  tapButton.getSize();

        TouchAction ta = new TouchAction(driver)
                .press(location.getX() + (size.getWidth() / 2), location.getY() + (size.getHeight() / 2))
                .release();
        for (int i = 0; i < CLICKS; i ++) {
            ta.perform();
        }
    }

    private void chooseHandAndFingerAndClickShowResults(Hand hand, Finger finger) {
        driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/dropMenuHand").click();
        switch (hand) {
            case LEFT:
                driverUtils.findElement(DriverUtils.Strategy.XPATH_NAME, "Left").click();
                break;
            case RIGHT:
                driverUtils.findElement(DriverUtils.Strategy.XPATH_NAME, "Right").click();
                break;
        }
        driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/dropMenuFinger").click();
        switch (finger) {
            case THUMB:
                driverUtils.findElement(DriverUtils.Strategy.XPATH_NAME, "Thumb").click();
                break;
            case INDEX:
                driverUtils.findElement(DriverUtils.Strategy.XPATH_NAME, "Index").click();
                break;
            case MIDDLE:
                driverUtils.findElement(DriverUtils.Strategy.XPATH_NAME, "Middle").click();
                break;
            case RING:
                driverUtils.findElement(DriverUtils.Strategy.XPATH_NAME, "Ring").click();
                break;
            case PINKY:
                driverUtils.findElement(DriverUtils.Strategy.XPATH_NAME, "Pinky").click();
                break;
        }

        driverUtils.findElement(DriverUtils.Strategy.ID, packageName + ":id/buttonShowResults").click();
    }
}
