package app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.LoginPage;
import pages.Routes;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private WebDriver browser;
    private String browserType;
    private HomePage homePage;
    private Routes routes;
    private LoginPage loginPage;

    public ApplicationManager(String browserType) {
        this.browserType = browserType;
    }

    public void init() {
        switch (browserType.toUpperCase()) {
            case "CHROME":
                browser = new ChromeDriver();
                break;
            case "FIREFOX":
                browser = new FirefoxDriver();
                break;
            case "IE":
                browser = new InternetExplorerDriver();
                break;
            default:
                System.out.println("No browser specified");
        }
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        homePage = new HomePage(browser);
        routes = new Routes(browser);
        loginPage = new LoginPage(browser);
    }

    public void stop() {
        browser.quit();
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public Routes getRoutes() {
        return routes;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }
}