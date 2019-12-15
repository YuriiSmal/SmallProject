package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends HomePage {

    private String h1Text = "xpath:>//*[@id=\"loginForm\"]/h1";
    private String log = "id:>strEmail";
    private String pass = "id:>strPassword";
    private String acceptBtn = "xpath:>//*[@class=\"mui-col-md-12\"]/button";

    public LoginPage(WebDriver browser) {
        super(browser);
    }

    public void isLoginPage() {
        Assert.assertTrue((findElement(h1Text).getText()).contains("Log In"), "Fail! Incorrect page!");
        System.out.println("All Right!");
    }

    public void signIn() {
        findElement(log).sendKeys(login);
        findElement(pass).sendKeys(password);
        findElement(acceptBtn).click();
        Assert.assertTrue(getCurrentUrl()
                .contains("/routes"), "Fail!");
        makeScreen("signIn");
        System.out.println("Sign In Ok!");
    }
}
