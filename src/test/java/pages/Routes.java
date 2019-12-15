package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Routes extends HomePage {

    private String search = "xpath:>//input[@placeholder=\"Search in My Routes\"]";
    private String listRoutes = "xpath:>//*[@role=\"alert\"]/tr[@data-route-id][%s]/td[@class=\"td_route_name\"]/a/span";
    private String circleLoarder = "xpath:>//*[@id=\"ab_processing\"][contains(@style, \"%s\")]";
    private String stops = "xpath:>//*[@id=\"viewDirectionsTable\"]//div[@class=\"direction-head\"][contains(text(), \"1)\")]";
    private String name = "xpath:>//*[@class=\"route-info-panel customizable\"]/div[@class=\"routeNameLeft\"]";

    public Routes(WebDriver browser) {
        super(browser);
    }

    public void checkElementInListByNumber(String number) {
        Assert.assertTrue(findElementByImplicitWait(listRoutes, number, 5)
                .isDisplayed(), "Fail! Create At least one route for test!");
        System.out.println("Route is Displaying!");
    }

    public void chooseRoutInList(String number) {
        findElementByImplicitWait(listRoutes, number, 5).click();
    }

    public void checkSearchByRouteName(String number) throws InterruptedException {
        waitCircleLoad(circleLoarder, "visible", 30);
        checkElementInListByNumber(number);
        String toSearch = findElement(listRoutes, number).getText();
        findElement(search).sendKeys(toSearch);
        waitCircleLoad(circleLoarder, "visible", 30);
        checkElementInListByNumber("1");
        Thread.sleep(3000);
        Assert.assertEquals(findElement(listRoutes, "1").getText(),
                toSearch, "Fail!");
        System.out.println("Search by Name are working! : " + toSearch + " is Ok!");
        makeScreen("searchByName");
        findElement(search).clear();
        waitCircleLoad(circleLoarder, "visible", 30);
    }

    public void checkSearchByStops(String number) throws InterruptedException {
        Thread.sleep(2000);
        chooseRoutInList(number);
        System.out.println("Route :" + number + " selected");
        String stop = findElementByUntilWait(stops).getText();
        String title = findElementByUntilWait(name).getText().toUpperCase()
                .replace(" " + login.toUpperCase(), "");
        backOnPrevPage();
        findElementByUntilWait(search).sendKeys(stop);
        waitCircleLoad(circleLoarder, "visible", 30);
        checkElementInListByNumber("1");
        Thread.sleep(3000);
        Assert.assertEquals(findElement(listRoutes, "1").getText().toUpperCase(),
                title, "Fail!");
        System.out.println("Search by Stop are working! : " + stop + " is Ok!");
        makeScreen("searchByStops");
        findElement(search).clear();
        waitCircleLoad(circleLoarder, "visible", 30);
    }
}
