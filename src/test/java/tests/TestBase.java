package tests;

import app.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.swing.*;

public class TestBase {

    public static final ApplicationManager appMan = new ApplicationManager("chrome");

    @BeforeSuite
    public void setBrowser() {
        appMan.init();
    }

    @AfterSuite
    public void closeBrowser() {
        appMan.stop();
        JOptionPane.showMessageDialog(null, "Test already done!");
    }
}