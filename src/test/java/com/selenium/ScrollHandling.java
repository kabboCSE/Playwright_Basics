package com.selenium;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Arrays;

public class ScrollHandling {
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

@Test ( priority = 0 )
    public void openUrl() throws InterruptedException {
        Thread.sleep(2000);
        page.navigate("https://testing-and-learning-hub.vercel.app/Selenium/pages/scroll.html");
        Thread.sleep(2000);
    }
    @Test ( priority = 1 )
//    @Test(dependsOnMethods = "openUrl")
    public void scrollToBottom() throws InterruptedException {
        String script = "window.scrollTo(0, document.body.scrollHeight);";
        page.evaluate(script);
        Thread.sleep(3000);

    }
    @Test (priority = 2)
   public void scrollToTop() throws InterruptedException {
        String script = "window.scrollTo(0, 0);";
        page.evaluate(script);
        Thread.sleep(3000);

    }


    @Test( priority = 3 )
    public void scrollToSpecificLocation() throws InterruptedException {
        ElementHandle element = page.querySelector("//h2[normalize-space()='Interactive Form']");
        element.scrollIntoViewIfNeeded();
        System.out.println(element.textContent());
        Thread.sleep(3000);


    }

    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();

    }
}
