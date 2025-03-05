package com.selenium;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Locators {
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

    @Test (priority = 0)
    public void openUrl() throws InterruptedException {
            Thread.sleep(2000);
            page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/registration_form.html");
        Thread.sleep(5000);
    }
    @Test (priority = 1)
    public void locators() throws InterruptedException {
        ElementHandle element = page.querySelector("#first-name");
        element.fill("Kabbo");
        Thread.sleep(5000);
    }


    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }

}
