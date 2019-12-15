package tests;

import org.testng.annotations.Test;

public class MainTests extends TestBase {

    // Open Main Page
    @Test
    public void goToMainPage() {
        appMan.getHomePage().openMainPage();
    }

    //Login with details from Login-Pass interface
    @Test(dependsOnMethods = {"goToMainPage"})
    public void signIn() {
        appMan.getHomePage().login();
        appMan.getLoginPage().isLoginPage();
        appMan.getLoginPage().signIn();
    }

    //Check that search be Route Name working!
    @Test(dependsOnMethods = {"signIn"})
    public void findRouteByName() throws InterruptedException {
        appMan.getRoutes().checkSearchByRouteName("2");
    }

    //Check that search be Route Stops working!
    @Test(dependsOnMethods = {"signIn"})
    public void findRouteByPoint() throws InterruptedException {
        appMan.getRoutes().checkSearchByStops("2");
    }
}
