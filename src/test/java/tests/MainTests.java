package tests;

import org.testng.annotations.Test;

public class MainTests extends TestBase {

    @Test
    public void goToMainPage() {
        appMan.getHomePage().openMainPage();

    }

    @Test(dependsOnMethods = {"goToMainPage"})
    public void signIn() {
        appMan.getHomePage().login();
        appMan.getLoginPage().isLoginPage();
        appMan.getLoginPage().signIn();
    }

    @Test(dependsOnMethods = {"signIn"})
    public void findRouteByName() {
        appMan.getRoutes().checkSearchByRouteName("2");
    }

    @Test(dependsOnMethods = {"signIn"})
    public void findRouteByPoint() throws InterruptedException {
        appMan.getRoutes().checkSearchByStops("2");
    }
}
