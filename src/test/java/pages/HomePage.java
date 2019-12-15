package pages;

import app.Helper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends Helper implements LoginPass {

    private String loginBtn = "xpath:>//*[@class=\"right-side\"]//a[@href=\"/login\"]";
    private String h1Text = "xpath:>//*[@class=\"container\"]//h1[contains(text(), \"Route Planning \")]";

    public HomePage(WebDriver browser) {
        super(browser);
    }

    public void openMainPage() {
        goTo(urlMainPage);
        Assert.assertTrue(findElement(h1Text).isDisplayed(), "Fail!");
        System.out.println("All Right!");
    }

    public void login () {
        findElement(loginBtn).click();

    }
}
