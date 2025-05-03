package com.selenium;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ScrollandClick {
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

    @Test
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://testing-and-learning-hub.vercel.app/index.html");
        Thread.sleep(5000);
    }
    @Test(priority = 1)
    public void scrollToSpecificLocation() throws InterruptedException {
        // Use Locator API instead of ElementHandle (safer and easier)
        Locator element = page.locator("//a[normalize-space()='Go to Table']");

        // Wait until the element is visible
        element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        // Scroll into view
        element.scrollIntoViewIfNeeded();
        Thread.sleep(1500);

        // Click and wait for navigation
        page.waitForNavigation(() -> {
            element.click();
        });

        // Optional: print URL or confirmation
        System.out.println("Clicked 'Go to Table' and navigated to: " + page.url());
        Thread.sleep(3000);
    }


    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
