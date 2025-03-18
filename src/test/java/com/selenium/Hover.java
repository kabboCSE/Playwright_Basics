package com.selenium;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;

public class Hover {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeSuite
    public void start(){
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList(new String[]{"--start-maximized"})));
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null)); // new season create
        page = browser.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    @Test
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/menu.html");
        Thread.sleep(5000);
    }
    @Test(dependsOnMethods = "openUrl")
    public void hoverHandling() throws InterruptedException {
        ElementHandle products = page.querySelector("//a[@id='productsDropdown']");
        products.hover();
        Thread.sleep(2000);

        ElementHandle categories = page.querySelector("//a[normalize-space()='Categories']");
        categories.hover();
        Thread.sleep(2000);

        ElementHandle electronics = page.querySelector("//a[normalize-space()='Electronics']");
        electronics.hover();
        Thread.sleep(2000);


    }


    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
