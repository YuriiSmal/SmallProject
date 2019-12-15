package app;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;


public class Helper {
    private WebDriver browser;
    static final long TIMEOUT_VALUE = 160;
    private WebDriverWait driverWait;

    public Helper(WebDriver browser) {
        this.browser = browser;

    }

    public WebElement findElement(String locator) {
        String[] locatorData = locator.split(":>");
        String locatorType = locatorData[0];
        String locatorValue = locatorData[1];
        By by = null;
        switch (locatorType) {
            case "xpath":
                by = By.xpath(locatorValue);
                break;
            case "css":
                by = By.cssSelector(locatorValue);
                break;
            case "id":
                by = By.id(locatorValue);
                break;
            case "name":
                by = By.name(locatorValue);
                break;
            case "link":
                by = By.linkText(locatorValue);
                break;
            default:
                System.out.println("Something went wrong: there is no such " + locatorType + " method!");
        }
        return browser.findElement(by);
    }

    public WebElement findElement(String locator, String value) {
        return findElement(String.format(locator, value));
    }

    public String getCurrentUrl() {
        return browser.getCurrentUrl();
    }

    public void goTo(String url) {
        browser.get(url);
    }

    public void checkUrlsResponse() throws IOException {
        List<WebElement> elements = browser.findElements(By.tagName("a"));
        for (WebElement element : elements) {
            String url = element.getAttribute("href");
            int resp = getResponse(url);
            Assert.assertTrue(resp == 200, "Url: " + url + " Not Correct! " + resp);
            System.out.println("Url: " + url + " Correct!");
        }
    }

    public int getResponse(String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
        openConnection.connect();
        int rCode = openConnection.getResponseCode();
        return rCode;
    }

    public long loadTime(String url) {
        long start = System.currentTimeMillis();
        browser.get(url);
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        return totalTime;
    }

    public void makeScreen(String name) {
        try {
            File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("Screens\\" + name + ".png"));
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        System.out.println("Screen created. Open Screen folder in project for watch!");
    }

    public void waitCircleLoad(String element, String status, int waitMilliS) {
        boolean isAvailable;
        long endTime = currentTimeMillis() + waitMilliS;
        do {
            isAvailable = findElementByImplicitWait(element, status, 1).isDisplayed();
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (currentTimeMillis() < endTime && isAvailable);
    }

    public void backOnPrevPage() {
        browser.navigate().back();
    }

    static void setImplicitlyWait(WebDriver driver, long value) {
        driver.manage().timeouts().implicitlyWait(value, TimeUnit.SECONDS);
    }

    public WebElement findElementByImplicitWait(String locator, String value, long waitSec) {
        setImplicitlyWait(browser, waitSec);
        WebElement webElement;
        try {
            webElement = findElement(locator, value);
        } catch (NoSuchElementException e) {
            setImplicitlyWait(browser, TIMEOUT_VALUE);
            return null;
        }
        setImplicitlyWait(browser, TIMEOUT_VALUE);
        return webElement;
    }

    public WebElement findElementByUntilWait(String locator) {
        driverWait = new WebDriverWait(browser, TIMEOUT_VALUE);
        return driverWait.until(ExpectedConditions.visibilityOf(findElement(locator)));
    }
}