package net.itarray.utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by zad on 08/06/17.
 */
public class DriverUtils {
    private AndroidDriver driver;

    public DriverUtils(AndroidDriver driver) {
        this.driver = driver;
    }

    public void swipeVertical(int durationInMilliseconds, double x, double startY, double endY) {
        int screnHeight = driver.manage().window().getSize().getHeight();
        int screnWidth = driver.manage().window().getSize().getWidth();

        x = screnWidth * x;
        startY = screnHeight * startY;
        endY = screnHeight * endY;

        if (durationInMilliseconds < 1000) {
            durationInMilliseconds = 1000;
        }

        new TouchAction(driver).press((int) x, (int) startY)
                .waitAction(Duration.ofMillis(durationInMilliseconds))
                .moveTo((int) x, (int) endY).release().perform();
    }

    public WebElement findElementVertically(Strategy strategy, String selector, int maxSwipe) {
        int swipes = 0;
        while (swipes < maxSwipe) {
            if (!elementExists(strategy, selector, 5)) {
                swipeVertical(1000, 0.5, 0.5, 0.1);
                swipes ++;
            } else {
                return findElement(strategy, selector);
            }
        }

        return null;
    }

    public boolean elementExists(Strategy strategy, String selector, int timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        try {
            return findElement(strategy, selector).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement findElement(Strategy strategy, String selector) {
        try {
            switch (strategy) {
                case ID:
                    return driver.findElementById(selector);
                case NAME:
                    return driver.findElementById(selector);
                case XPATH:
                    return driver.findElementByXPath(selector);
                case CLASS_NAME:
                    return driver.findElementByClassName(selector);
                case XPATH_NAME:
                    return driver.findElementByXPath(String.format("//*[@text='%s' or @content-desc='%s']", selector, selector));
                default:
                    return null;
            }
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public enum Strategy {
        ID,
        NAME,
        XPATH,
        CLASS_NAME,
        XPATH_NAME
    }
}
