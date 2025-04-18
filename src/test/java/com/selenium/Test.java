package com.selenium;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Test {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeSuite
    public void start(){
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(); // new season create
        page = browser.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    @org.testng.annotations.Test
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        Thread.sleep(5000);
    }

    @org.testng.annotations.Test(priority = 1)
    public void clickOnLoginButton() throws InterruptedException {
        ElementHandle element = page.querySelector("input[type='submit'][value='Login']");
        element.click();
        Thread.sleep(4000);
    }



    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
