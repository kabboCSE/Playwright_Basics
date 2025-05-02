package com.selenium;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;


public class NavigationCommand {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeClass
    public void start() {
        playwright = Playwright.create();
        browserType = playwright.chromium();

        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));

        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));

        page = browserContext.newPage();
        System.out.println("Browser version: " + browser.version());
    }

   @Test(priority = 1)
    public void loginFlow() throws InterruptedException {
        LoginHelper loginHelper = new LoginHelper(page);
        loginHelper.performLogin();
        Thread.sleep(2000);
    }
    @Test(priority = 2, dependsOnMethods = "loginFlow")
    public void clickAutomation() throws InterruptedException {
        page.waitForSelector("//a[@href='../WebAutomation/selenium.html']",
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("//a[@href='../WebAutomation/selenium.html']");
        Thread.sleep(2000);
    }


    @Test(priority = 2, dependsOnMethods = "clickAutomation")
    public void clickGetStartedBasic() throws InterruptedException {
        page.waitForSelector("a[href='/WebAutomation/contents/basic.html']",
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("a[href='/WebAutomation/contents/basic.html']");
        Thread.sleep(2000);
    }

    @Test(priority = 3, dependsOnMethods = "clickGetStartedBasic")
    public void scrollToSpecificLocationElements() throws InterruptedException {
        Locator element = page.locator("//a[normalize-space()='Go to Elements']");

        // Optional: check if element is visible before scrolling
        element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        element.scrollIntoViewIfNeeded();
        Thread.sleep(2000);

        System.out.println(element.textContent());
        Thread.sleep(2000);

        element.click();
        Thread.sleep(2000);
    }
    @Test(priority = 4)
    public void navigations() throws InterruptedException {
        page.click("//a[@href='../../home.html']");
        Thread.sleep(2000);

        //Back
        page.goBack();
        Thread.sleep(2000);
        //Refresh
        page.reload();
        Thread.sleep(2000);
        //Forward
        page.goForward();
        Thread.sleep(2000);
    }

    @AfterClass
    public void stop() {
        page.close();
        browser.close();
        playwright.close();
    }
}
